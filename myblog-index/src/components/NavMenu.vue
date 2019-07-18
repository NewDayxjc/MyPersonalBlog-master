<template >
   <el-container class="animated fadeInDown
   line-height: 55px; position" >
    <el-header style="height:50px">
          <div class="grid-content bg-purple-light">
            <el-menu class="el-menu-bar" mode="horizontal" @select="handleSelect">
              <el-menu-item index="1"><router-link to="/">首页</router-link></el-menu-item>
              <el-menu-item disabled index="2"><a href="#" class="link">随笔</a></el-menu-item>
              <el-menu-item disabled index="3"><a href="#" class="link">相册</a></el-menu-item>
              <el-menu-item disabled index="4"><a href="#" class="link">视频</a></el-menu-item>
              <el-menu-item index="5"><a href="#" class="link">关于我</a></el-menu-item>
              <el-menu-item index="6" style="float:right;pading-right:0px;"><span @click="toAdmin">控制台</span></el-menu-item>
             <el-menu-item> 
               <el-input
                ref="input"
                  placeholder="请输入内容"
                  v-model="searchInput"
                  clearable  
                  @focus="changeWidth(1)"
                  @blur="changeWidth(2)"
                  style="outline:none; background: rgb(255,255,255)"
                  :style="{'width': inputW}"
                  suffix-icon="el-icon-search"
                  >

                </el-input>
             </el-menu-item>
            </el-menu>
          </div>
    </el-header>
  </el-container>
</template>

<script>
  export default {
    data() {
      return {
      searchInput: '',
      inputW: '200px',
      adminUrl:'http://39.96.94.165:8000/#/write'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        // console.log(key, keyPath);
      },
      changeWidth(n){
        if(n==1){
        this.inputW = '250px'
        }else{
        this.inputW = '200px' 
        }
      },
       watchScroll () {
        var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        //  当滚动超过 50 时，实现吸顶效果
        if (scrollTop > 49) {
          this.navBarFixed = true
        } else {
          this.navBarFixed = false
        }
     },
     toAdmin(){
       window.location.href = this.adminUrl;
    }

    },
    mounted() {
         // 事件监听滚动条
      window.addEventListener('scroll', this.watchScroll)
    }
  }
</script>
<style >
.el-input__inner {
      height: 37px;
}
  .position {
    position: fixed;
    top: 0;
    z-index: 999;
    width: 100%;
    }
  .el-container.is-vertical {
      border-bottom: solid 1px #e6e6e6;
      box-shadow: 0 0px 10px rgba(0,0,0,.05);
  }
  .el-menu--horizontal>.el-menu-item {
    float: left;
    line-height: 50px;
    height: 50px;
  }
  .el-input--suffix .el-input__inner {
      border-radius: 20px;
      background: rgb(238, 238, 238);
      /* border: rgb(238, 238, 238); */
  }
  .el-input {
      transition: width 0.6s;  
      border: rgb(238, 238, 238) !important;
  }
  .el-menu--horizontal>.el-menu-item.is-active {
        border-bottom: 1px solid #409EFF
  }
  .el-menu--horizontal>.el-menu-item {

    color: rgb(0, 0, 0) !important;
}
  .el-header{
    padding: 0px 50px;
  }
  .el-header, .el-footer {
    background-color: rgb(255, 255, 255);
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-header.el-
  line-height: 55px;row {
    height: 50px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
   .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>