<template>
    <div>
        <el-select placeholder="请选择" v-model="brandId" filterable clearable>
            <el-option
                v-for="item in brands"
                :key="item.brandId"
                :label="item.name"
                :value="item.brandId"
            ></el-option>
        </el-select>
    </div>
</template>

<script>
export default {
    components: {},
    props: {},
    data() {
        //这里存放数据
        return {
            catId: 0,
            brands: [],
            brandId: null,
            subscribe: null
        };
    },
    computed: {},
    //监控data中的数据变化
    watch: {
        brandId(val) {
            this.PubSub.publish("brandId", val);
        }
    },
    //方法集合
    methods: {
        getCatBrands() {
            this.$http({
                url: "/product/categorybrandrelation/brands/list",
                method: "get",
                params: this.$http.adornParams({
                    catId: this.catId
                })
            }).then(({data}) => {
                this.brands = data.data;
            });
        }
    },
    created() {
    },
    mounted() {
        //监听三级分类消息的变化
        this.subscribe = this.PubSub.subscribe("catPath", (msg, val) => {
            console.log(msg, val);
            this.catId = val[val.length - 1];
            this.getCatBrands();
        });
    },
    beforeCreate() {
    },
    beforeMount() {
    },
    beforeUpdate() {
    },
    updated() {
    },
    beforeDestroy() {
        this.PubSub.unsubscribe(this.subscribe); //销毁订阅
    },
    destroyed() {
    },
    activated() {
    }
};
</script>
<style scoped>
</style>
