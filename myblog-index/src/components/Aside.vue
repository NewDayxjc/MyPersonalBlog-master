<template>
     <el-col :md="6" >
          <el-card class="aside-card animated fadeInUp" shadow="hover">
            <div style=" padding-top:15px;">
                <el-image :src="src" class="avatar">
                </el-image>
            </div>
            <span class="is-size-4">huangh</span><br>
            <span class="is-size-6">Web Developer</span><br>
            <i class="el-icon-location-outline"><span class="is-size-3">中国,广东,广州</span></i>
            <div class="tag-bar">
                <div class="item">
                    <span style="margin: 0 7px;">POST</span>
                <div>{{information.post}}</div>
                </div>
                <div class="item">
                    <span style="margin: 0 7px;">CATEGORYS</span> 
                <div>{{information.categorys}}</div>
                </div>
                <div class="item">
                    <span style="margin: 0 7px;">VISIT</span>
                <div>{{information.visit}}</div>
                </div>
            </div>
            <div class="icon-link">
             
            </div>
          </el-card>
          <el-card class="aside-card animated fadeInUp" shadow="always">
            <div class="web-site-message-head">
                categories
            </div>  
            <div class="category-item">
               <ul>
                   <li v-for="item in categoryList" >
                       <div class="category-title">{{item.name}}</div>
                       <div class="category-num">{{item.number}}</div>
                   </li>
                  
               </ul>
            </div>
          </el-card>
        </el-col>
</template>
<script>
    import { getCategory } from '../api/categoryApi';

export default {
    data(){
        return{
            information:{
                post:0,
                categorys:0,
                visit:0,
            },
            categoryList:{},
            src: 'http://39.96.94.165:8888/group1/M00/00/00/rBADI1z7ecaAWIbHAAmmMH2Suqc019.png'
        }
    },
    methods:{
        getCategoryList(){
            getCategory().then(result=>{
                // console.log(result.data)
                this.categoryList = result.data.data
                this.information.categorys = result.data.data.length
                result.data.data.forEach(element => {
                    // console.log("===--->",element)
                    this.information.post+=element.number;
                }); 
            })
        }
    },
    mounted(){
        this.getCategoryList();
    }
}
</script>
<style scoped>
.icon-link{ 
    display: flex;
}
.category-item .category-num{
    float: right;
    width: 35px;
    height: 27px;
    text-align: center;
    background-color: whitesmoke;
    border-radius: 4px;
    color: #000000;
    font-size: 0.75rem;
}
.category-item .category-title{
    height: 27px;
    font-size: 0.90rem;
}
.category-item{
    float: left;
    width: 100%;
    line-height: 22px;
}
.category-item ul{
    font-size: 15px !important;
    letter-spacing: 2px;
}
.category-item li{
    border-radius:0 0 0 4px;
    width: 85%;
    height: 22px;
    display: flex;
    justify-content: space-between;
    line-height: 33px;
    transition: background 0.5s;
    border-left: 1px rgb(219, 219, 219) solid;
    padding-left:20px;
    padding-bottom: 10px;
    margin-bottom: 5px;
    text-align: left;
}
.category-item li:hover{
    transition: background 0.5s;
    background: rgb(245, 245, 245);
    cursor: pointer;
}
 .web-site-message-head{
    display: block;
    float: left;
    width: 100%;
    text-align: left;
    font-family: 'Arial','Microsoft YaHei','黑体','宋体',sans-serif;
    padding-left:  20px;
    padding-top: 15px;
    text-transform: uppercase;
    font-weight: 500 !important;
    font-size: 11px;
    letter-spacing: 1px;
}
    .tag-bar{
        display:flex;
        margin: 10px;
        font-size: 11px !important;
    }
    .tag-bar .item{
        flex: 1;
    }
    .is-size-3{
        font-size: 0.6rem !important;
        margin-top: 10px;
    }
    .is-size-4 {
        font-size: 1.3rem !important;
        margin-top: 10px;
    }.is-size-6 {
        font-size: 0.8rem !important;
        margin-top: 10px;
    }
    .avatar{
        border-radius: 50%;
        height: 110px;
        width: 110px;
        cursor: pointer;
        transition: all 2.0s;
    }
    .avatar:hover{
        transform: rotate(360deg);
    
    }
    .el-col-md-6 {
        width: 24%;
    }
    .aside-card{
        /* height: 300px; */
        margin-bottom: 20px;
        border-radius:4px;
        box-shadow: 0 4px 10px rgba(0,0,0,.05);
    }
</style>
