<template>
    <div>
        <el-input placeholder="输入关键字进行过滤" v-model="filterText"></el-input>
        <el-tree :data="menus"
                 :props="defaultProps"
                 @node-click="handleNodeClick"
                 node-key="catId"
                 :filter-node-method="filterNode"
                 highlight-current
                 ref="menuTree">
        </el-tree>
    </div>
</template>

<script>
export default {
    data() {
        return {
            filterText: "",
            menus: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            }
        }
    },
    watch: {
        filterText(val) {
            this.$refs.menuTree.filter(val);
        }
    },
    methods: {
        //树节点过滤
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        },
        handleNodeClick(data, node, component) {
            this.$emit('node-click', data, node, component);
        },
        getMenus() {
            this.$http({
                url: '/product/category/list/tree',
                method: 'get'
            }).then(({data}) => {
                this.menus = data.data
            })
        },
    },
    mounted() {
        this.getMenus();
    }
}
</script>

<style scoped>

</style>
