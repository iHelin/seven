<template>
    <div>
        <el-switch
            v-model="draggable"
            active-text="开启拖拽"
            inactive-text="关闭拖拽">
        </el-switch>
        <el-button v-if="draggable" @click="batchSave">批量保存</el-button>
        <el-button type="danger" @click="batchDelete">批量删除</el-button>
        <el-tree :data="menus"
                 :props="defaultProps"
                 :expand-on-click-node="false"
                 show-checkbox
                 node-key="catId"
                 :default-expanded-keys="expandedKeys"
                 :draggable="draggable"
                 ref="tree"
                 :allow-drop="allowDrop"
                 @node-drop="handleDrop"
                 @node-click="handleNodeClick">
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button
                        v-if="node.level<=2"
                        type="text"
                        size="mini"
                        @click="() => append(data)">
                    Append
                    </el-button>
                    <el-button
                        type="text"
                        size="mini"
                        @click="() => edit(data)">
                    Edit
                    </el-button>
                    <el-button
                        v-if="!data.children.length"
                        type="text"
                        size="mini"
                        @click="() => remove(node, data)">
                    Delete
                    </el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog
            :close-on-click-modal="false"
            :title="category.catId===null?'添加':'修改'"
            :visible.sync="dialogVisible"
            width="30%">
            <el-form :model="category">
                <el-form-item label="分类名称">
                    <el-input v-model="category.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="图标">
                    <el-input v-model="category.icon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="计量单位">
                    <el-input v-model="category.productUnit" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitData()">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
    data() {
        return {
            category: {
                name: '',
                parentCid: 0,
                catLevel: 0,
                showStatus: 1,
                sort: 0,
                catId: null,
                icon: '',
                productUnit: ''
            },
            dialogVisible: false,
            menus: [],
            expandedKeys: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            maxLevel: 0,
            updateNodes: [],
            draggable: false,
            pCid: []
        };
    },
    methods: {
        batchDelete() {
            let checkedNodes = this.$refs['tree'].getCheckedNodes();
            let ids = checkedNodes.map(item => item.catId);
            this.$confirm(`是否批量删除？`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http({
                    url: '/product/category/delete',
                    method: 'delete',
                    data: this.$http.adornData(ids, false)
                }).then(({data}) => {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.getMenus();
                })
            }).catch(() => {

            })
        },
        batchSave() {
            this.$http({
                url: "/product/category/update/sort",
                method: 'post',
                data: this.$http.adornData(this.updateNodes, false)
            }).then(({resp}) => {
                this.$message({
                    message: '修改成功',
                    type: 'success'
                })
                //刷新菜单
                this.getMenus();
                this.expandedKeys = this.pCid;
                this.pCid = [];
            })
        },
        handleDrop(draggingNode, dropNode, dropType, ev) {
            this.updateNodes = [];
            //当前节点最新的pCid
            let pCid = 0;
            let siblings = [];
            if (dropType === 'inner') {
                pCid = dropNode.data.catId;
                siblings = dropNode.childNodes;
            } else {
                pCid = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId;
                siblings = dropNode.parent.childNodes;
            }
            this.pCid.push(pCid);

            //当前节点的最新顺序
            for (let i = 0; i < siblings.length; i++) {
                if (siblings[i].data.catId === draggingNode.data.catId) {
                    //如果遍历的是当前正在拖拽的节点
                    let catLevel = draggingNode.level;
                    if (siblings[i].level !== draggingNode.level) {
                        catLevel = siblings[i].level;
                        this.updateChildNodeLevel(siblings[i]);
                    }
                    this.updateNodes.push({
                        catId: siblings[i].data.catId,
                        sort: i,
                        parentCid: pCid,
                        catLevel
                    });
                } else {
                    this.updateNodes.push({
                        catId: siblings[i].data.catId,
                        sort: i
                    });
                }
            }

        },
        updateChildNodeLevel(node) {
            if (node.childNodes != null && node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    let cNode = node.childNodes[i];
                    this.updateNodes.push({
                        catId: cNode.data.catId,
                        catLevel: cNode.level
                    });
                    this.updateChildNodeLevel(cNode);
                }
            }
        },
        allowDrop(draggingNode, dropNode, dropType) {
            this.maxLevel = 0;
            this.countNodeLevel(draggingNode);

            let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
            if (dropType === 'inner') {
                return deep + dropNode.level <= 3;
            } else {
                return deep + dropNode.parent.level <= 3;
            }
        },
        countNodeLevel(node) {
            if (node.childNodes != null && node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    if (node.childNodes[i].level > this.maxLevel) {
                        this.maxLevel = node.childNodes[i].level;
                    }
                    this.countNodeLevel(node.childNodes[i]);
                }
            }
        },
        submitData() {
            if (this.category.catId === null) {
                this.addCategory();
            } else {
                this.editCategory();
            }
        },
        editCategory() {
            let {catId, name, icon, productUnit} = this.category;
            this.$http({
                url: "/product/category/update",
                method: 'post',
                data: this.$http.adornData({catId, name, icon, productUnit}, false)
            }).then(({resp}) => {
                this.$message({
                    message: '保存成功',
                    type: 'success'
                })
                this.getMenus();
                this.expandedKeys = [this.category.parentCid];
                this.dialogVisible = false;
            })
        },
        addCategory() {
            this.$http({
                url: "/product/category/save",
                method: 'post',
                data: this.$http.adornData(this.category, false)
            }).then(({resp}) => {
                this.$message({
                    message: '保存成功',
                    type: 'success'
                })
                this.getMenus();
                this.expandedKeys = [this.category.parentCid];
                this.dialogVisible = false;
            })
        },
        handleNodeClick(data) {
        },
        append(data) {
            this.category.catId = null;
            this.category.name = '';
            this.category.icon = '';
            this.category.productUnit = '';

            this.category.parentCid = data.catId;
            this.category.catLevel = data.catLevel + 1;
            this.dialogVisible = true;
        },
        edit(menu) {
            this.category.catId = menu.catId;
            this.category.parentCid = menu.catId;
            this.$http({
                url: `/product/category/info/${menu.catId}`,
                method: 'get'
            }).then(({data}) => {
                this.category.name = data.data.name;
                this.category.icon = data.data.icon;
                this.category.productUnit = data.data.productUnit;
                this.dialogVisible = true;
            });
        },
        getMenus() {
            this.$http({
                url: '/product/category/list/tree',
                method: 'get'
            }).then(({data}) => {
                this.menus = data.data
            })
        },
        remove(node, data) {
            this.$confirm(`是否删除【${data.name}】菜单？`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let ids = [data.catId]
                this.$http({
                    url: '/product/category/delete',
                    method: 'delete',
                    data: this.$http.adornData(ids, false)
                }).then(() => {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    })
                    this.getMenus()
                    this.expandedKeys = [data.parentCid]
                })
            }).catch(() => {

            })
        }
    },
    mounted() {
        this.getMenus()
    }
}
</script>

<style scoped>
</style>
