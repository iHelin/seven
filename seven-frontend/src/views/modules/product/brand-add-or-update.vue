<template>
    <el-dialog
        :title="dataForm.brandId ? '修改' : '新增'"
        :close-on-click-modal="false"
        :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                 label-width="120px">
            <el-form-item label="品牌名称" prop="name">
                <el-input v-model="dataForm.name" placeholder="品牌名称"></el-input>
            </el-form-item>
            <el-form-item label="品牌logo地址" prop="logo">
                <!--                <el-input v-model="dataForm.logo" placeholder="品牌logo地址"></el-input>-->
                <SingleUpload v-model="dataForm.logo"></SingleUpload>
            </el-form-item>
            <el-form-item label="介绍" prop="descript">
                <el-input v-model="dataForm.descript" placeholder="介绍"></el-input>
            </el-form-item>
            <el-form-item label="显示状态" prop="showStatus">
                <el-switch
                    v-model="dataForm.showStatus"
                    :active-value="1"
                    :inactive-value="0"
                    active-color="#13ce66"
                    inactive-color="#ff4949">
                </el-switch>
            </el-form-item>
            <el-form-item label="检索首字母" prop="firstLetter">
                <el-input v-model="dataForm.firstLetter" placeholder="检索首字母"></el-input>
            </el-form-item>
            <el-form-item label="排序" prop="sort">
                <el-input v-model.number="dataForm.sort" placeholder="排序"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
import SingleUpload from 'src/components/upload/singleUpload'

export default {
    components: {
        SingleUpload
    },
    data() {
        return {
            visible: false,
            dataForm: {
                brandId: 0,
                name: '',
                logo: '',
                descript: '',
                showStatus: 1,
                firstLetter: '',
                sort: 0
            },
            dataRule: {
                name: [
                    {required: true, message: '品牌名称不能为空', trigger: 'blur'}
                ],
                logo: [
                    {required: true, message: '品牌logo地址不能为空', trigger: 'blur'}
                ],
                descript: [
                    {required: true, message: '介绍不能为空', trigger: 'blur'}
                ],
                showStatus: [
                    {required: true, message: '显示状态不能为空', trigger: 'blur'}
                ],
                firstLetter: [
                    {
                        validator: (rule, value, callback) => {
                            if (value === "") {
                                callback(new Error("首字母必须填写"));
                            } else if (!/^[a-zA-Z]$/.test(value)) {
                                callback(new Error("首字母必须是a-z或者A-Z之间"));
                            } else {
                                callback();
                            }
                        }, trigger: 'blur'
                    }
                ],
                sort: [
                    {
                        validator: (rule, value, callback) => {
                            if (value === "") {
                                callback(new Error("排序字段必须填写"));
                            } else if (!Number.isInteger(value) || value < 0) {
                                callback(new Error("排序字段必须是一个大于等于0的整数"));
                            } else {
                                callback();
                            }
                        }, trigger: 'blur'
                    }
                ]
            }
        }
    },
    methods: {
        init(id) {
            this.dataForm.brandId = id;
            this.visible = true;
            this.$nextTick(() => {
                this.$refs['dataForm'].resetFields()
                if (this.dataForm.brandId) {
                    this.$http({
                        url: `/product/brand/info/${this.dataForm.brandId}`,
                        method: 'get',
                        params: this.$http.adornParams()
                    }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.dataForm.name = data.data.name
                            this.dataForm.logo = data.data.logo
                            this.dataForm.descript = data.data.descript
                            this.dataForm.showStatus = data.data.showStatus
                            this.dataForm.firstLetter = data.data.firstLetter
                            this.dataForm.sort = data.data.sort
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
                        url: `/product/brand/${this.dataForm.brandId ? 'update' : 'save'}`,
                        method: this.dataForm.brandId ? 'put' : 'post',
                        data: {
                            "brandId": this.dataForm.brandId,
                            "name": this.dataForm.name || undefined,
                            "logo": this.dataForm.logo || undefined,
                            "descript": this.dataForm.descript || undefined,
                            "showStatus": this.dataForm.showStatus,
                            "firstLetter": this.dataForm.firstLetter || undefined,
                            "sort": this.dataForm.sort
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
    }
}
</script>
