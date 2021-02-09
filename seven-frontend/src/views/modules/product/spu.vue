<template>
    <div>
        <el-row>
            <el-col :span="24">
                <el-form :inline="true" :model="dataForm">
                    <el-form-item label="分类">
                        <category-cascader :catalogPath.sync="catalogPath"></category-cascader>
                    </el-form-item>
                    <el-form-item label="品牌">
                        <brand-select style="width:120px"></brand-select>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select style="width:120px" v-model="dataForm.status" clearable>
                            <el-option label="新建" :value="0"></el-option>
                            <el-option label="上架" :value="1"></el-option>
                            <el-option label="下架" :value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="检索">
                        <el-input style="width:160px" v-model="dataForm.key" clearable></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="searchSpuInfo">查询</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24">
                <spuinfo :catId="catId"></spuinfo>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import CategoryCascader from "../common/category-cascader";
import BrandSelect from "../common/brand-select";
import Spuinfo from "./spuinfo";

export default {
    components: {CategoryCascader, Spuinfo, BrandSelect},
    props: {},
    data() {
        //这里存放数据
        return {
            catId: 0,
            catalogPath: [],
            dataForm: {
                status: null,
                key: "",
                brandId: null,
                catalogId: null
            },
            catPathSub: null,
            brandIdSub: null

        };
    },
    //计算属性类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        searchSpuInfo() {
            console.log("搜索条件", this.dataForm);
            this.PubSub.publish("dataForm", this.dataForm);
        }
    },
    created() {
    },
    mounted() {
        this.catPathSub = this.PubSub.subscribe("catPath", (msg, val) => {
            this.dataForm.catalogId = val[val.length - 1];
        });
        this.brandIdSub = this.PubSub.subscribe("brandId", (msg, val) => {
            console.log('brand', msg, val);
            this.dataForm.brandId = val;
        });
    },
    beforeCreate() {
    }, //生命周期-创建之前
    beforeMount() {
    }, //生命周期-挂载之前
    beforeUpdate() {
    }, //生命周期-更新之前
    updated() {
    }, //生命周期-更新之后
    beforeDestroy() {
        this.PubSub.unsubscribe(this.catPathSub);
        this.PubSub.unsubscribe(this.brandIdSub);
    }, //生命周期-销毁之前
    destroyed() {
    }, //生命周期-销毁完成
    activated() {
    } //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style scoped>
</style>
