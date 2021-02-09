<template>
    <el-dialog
        :title="dataForm.attrGroupId ? '修改' : '新增'"
        :close-on-click-modal="false"
        @close="dataForm.catalogIds=[]"
        :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="80px">
            <el-form-item label="分组名称" prop="attrGroupName">
                <el-input v-model="dataForm.attrGroupName" placeholder="分组名称"></el-input>
            </el-form-item>
            <el-form-item label="排序" prop="sort">
                <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="descript">
                <el-input v-model="dataForm.descript" placeholder="描述"></el-input>
            </el-form-item>
            <el-form-item label="图标" prop="icon">
                <el-input v-model="dataForm.icon" placeholder="图标"></el-input>
            </el-form-item>
            <el-form-item label="分类id" prop="catalogIds">
                <el-cascader
                    filterable
                    placeholder="试试搜索：手机"
                    v-model="dataForm.catalogIds"
                    :props="{label:'name',value:'catId',children:'children'}"
                    :options="categories">
                </el-cascader>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
export default {
    data() {
        return {
            visible: false,
            dataForm: {
                attrGroupId: 0,
                attrGroupName: '',
                sort: '',
                descript: '',
                icon: '',
                catalogIds: [],
                catalogId: ''
            },
            dataRule: {
                attrGroupName: [
                    {required: true, message: '不能为空', trigger: 'blur'}
                ],
                sort: [
                    {required: true, message: '不能为空', trigger: 'blur'}
                ],
                descript: [
                    {required: true, message: '不能为空', trigger: 'blur'}
                ],
                icon: [
                    {required: true, message: '不能为空', trigger: 'blur'}
                ],
                catalogIds: [
                    {required: true, message: '不能为空', trigger: 'blur'}
                ]
            },
            categories: []
        }
    },
    methods: {
        getMenus() {
            this.$http({
                url: '/product/category/list/tree',
                method: 'get'
            }).then(({data}) => {
                this.categories = data.data
            })
        },
        init(id) {
            this.dataForm.attrGroupId = id;
            this.visible = true;
            this.$nextTick(() => {
                this.$refs['dataForm'].resetFields()
                if (this.dataForm.attrGroupId) {
                    this.$http({
                        url: `/product/attrgroup/info/${this.dataForm.attrGroupId}`,
                        method: 'get',
                        params: {}
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.dataForm.attrGroupName = data.data.attrGroupName
                            this.dataForm.sort = data.data.sort
                            this.dataForm.descript = data.data.descript
                            this.dataForm.icon = data.data.icon
                            this.dataForm.catalogId = data.data.catalogId
                            this.dataForm.catalogIds = data.data.catalogIds
                        }
                    })
                }
            })
        },
        // 表单提交
        dataFormSubmit() {
            this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                    this.$http({
                        url: `/product/attrgroup/${this.dataForm.attrGroupId ? 'update' : 'save'}`,
                        method: this.dataForm.attrGroupId ? 'put' : 'post',
                        data: {
                            "attrGroupId": this.dataForm.attrGroupId,
                            "attrGroupName": this.dataForm.attrGroupName,
                            "sort": this.dataForm.sort,
                            "descript": this.dataForm.descript,
                            "icon": this.dataForm.icon,
                            "catalogId": this.dataForm.catalogIds[this.dataForm.catalogIds.length - 1],
                        }
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.$message({
                                message: '操作成功',
                                type: 'success',
                                duration: 1500,
                                onClose: () => {
                                    this.visible = false
                                    this.$emit('refreshDataList')
                                }
                            })
                        } else {
                            this.$message.error(data.msg)
                        }
                    })
                }
            })
        }
    },
    mounted() {
        this.getMenus();
    }
}
</script>
