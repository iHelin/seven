<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="16">
                <el-card>
                    <el-tabs tab-position="left">
                        <el-tab-pane
                            v-for="(group,gidx) in dataResp.attrGroups"
                            :label="group.attrGroupName"
                            :key="group.attrGroupId">
                            <!-- 遍历属性,每个tab-pane对应一个表单，每个属性是一个表单项  spu.baseAttrs[0] = [{attrId:xx,val:}]-->
                            <el-form :model="dataResp">
                                <el-form-item
                                    :label="attr.attrName"
                                    v-for="(attr,aidx) in group.attrs"
                                    :key="attr.attrId">
                                    <el-select
                                        v-model="dataResp.baseAttrs[gidx][aidx].attrValues"
                                        :multiple="attr.valueType === 1"
                                        filterable
                                        allow-create
                                        default-first-option
                                        placeholder="请选择或输入值">
                                        <el-option
                                            v-for="(val,vidx) in attr.valueSelect.split(';')"
                                            :key="vidx"
                                            :label="val"
                                            :value="val">
                                        </el-option>
                                    </el-select>
                                    <el-checkbox
                                        style="margin-left: 10px;"
                                        v-model="dataResp.baseAttrs[gidx][aidx].showDesc"
                                        :true-label="1"
                                        :false-label="0">
                                        快速展示
                                    </el-checkbox>
                                </el-form-item>
                            </el-form>
                        </el-tab-pane>
                    </el-tabs>
                    <div style="margin-bottom:20px;float:right">
                        <el-button type="success" @click="submitSpuAttrs">确认修改</el-button>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
    components: {},
    props: {},
    data() {
        return {
            spuId: "",
            catalogId: "",
            dataResp: {
                //后台返回的所有数据
                attrGroups: [],
                baseAttrs: []
            },
            spuAttrsMap: {}
        };
    },
    computed: {},
    methods: {
        clearData() {
            this.dataResp.attrGroups = [];
            this.dataResp.baseAttrs = [];
            this.spuAttrsMap = {};
        },
        getSpuBaseAttrs() {
            this.$http({
                url: `/product/attr/base/listforspu/${this.spuId}`,
                method: "get"
            }).then(({data}) => {
                data.data.forEach(item => {
                    this.spuAttrsMap["" + item.attrId] = item;
                });
            });
        },
        getQueryParams() {
            this.spuId = this.$route.query.spuId;
            this.catalogId = this.$route.query.catalogId;
            console.log("----", this.spuId, this.catalogId);
        },
        showBaseAttrs() {
            let _this = this;
            this.$http({
                url: `/product/attrgroup/${this.catalogId}/withattr`,
                method: "get",
                params: this.$http.adornParams({})
            }).then(({data}) => {
                //先对表单的baseAttrs进行初始化
                data.data.forEach(item => {
                    let attrArray = [];
                    item.attrs.forEach(attr => {
                        let v = "";
                        if (_this.spuAttrsMap["" + attr.attrId]) {
                            v = _this.spuAttrsMap["" + attr.attrId].attrValue.split(";");
                            if (v.length === 1) {
                                v = v[0] + "";
                            }
                        }
                        attrArray.push({
                            attrId: attr.attrId,
                            attrName: attr.attrName,
                            attrValues: v,
                            showDesc: _this.spuAttrsMap["" + attr.attrId]
                                ? _this.spuAttrsMap["" + attr.attrId].quickShow
                                : attr.showDesc
                        });
                    });
                    this.dataResp.baseAttrs.push(attrArray);
                });
                this.dataResp.attrGroups = data.data;
            });
        },
        submitSpuAttrs() {
            let submitData = [];
            this.dataResp.baseAttrs.forEach(item => {
                item.forEach(attr => {
                    let val = "";
                    if (attr.attrValues instanceof Array) {
                        val = attr.attrValues.join(";");
                    } else {
                        val = attr.attrValues;
                    }
                    if (val !== "") {
                        submitData.push({
                            attrId: attr.attrId,
                            attrName: attr.attrName,
                            attrValue: val,
                            quickShow: attr.showDesc
                        });
                    }
                });
            });

            this.$confirm("修改商品规格信息, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: `/product/attr/update/${this.spuId}`,
                        method: "put",
                        data: this.$http.adornData(submitData, false)
                    }).then(({data}) => {
                        this.$message({
                            type: "success",
                            message: "属性修改成功!"
                        });
                    });
                })
                .catch((e) => {
                    this.$message({
                        type: "info",
                        message: "已取消修改" + e
                    });
                });
        }
    },
    created() {
    },
    activated() {
        this.clearData();
        this.getQueryParams();
        if (this.spuId && this.catalogId) {
            this.showBaseAttrs();
            this.getSpuBaseAttrs();
        }
    }
};
</script>
<style scoped>
</style>
