<template >
  <section> 
    <!-- 导航栏 -->
    <NavMenuComponent></NavMenuComponent>
  <el-container class="content-body " >
    <!-- <el-row :gutter="20"> -->
      <!-- left侧边栏 -->
      <AsideComponent></AsideComponent>
      <!-- 中间部分 -->
      <ContentComponent></ContentComponent>
      <!--right 侧边栏  -->
      <!-- <AsideRightComponent></AsideRightComponent> -->
    <!-- </el-row> -->
  </el-container>
    <el-tooltip placement="top" content="回到顶部">
      <back-to-top :custom-style="myBackToTopStyle" :visibility-height="300" :back-position="0" transition-name="fade"/>
    </el-tooltip>
    <!-- footer -->
  <FooterComponent></FooterComponent>
  </section>

</template>
  
<script>
    //1.先使用import导入你要在该组件中使用的子组件
    import NavMenuComponent from '@/components/NavMenu.vue'
    import AsideComponent from '@/components/Aside.vue'
    import ContentComponent from '@/components/ContentBody.vue'
    import AsideRightComponent from '@/components/AsideRight.vue'
    import FooterComponent from '@/components/Footer.vue'
    import BackToTop from '@/components/BackToTop'

    export default {
        //2.然后,在components中写入子组件
        components: {FooterComponent,NavMenuComponent,AsideComponent,ContentComponent,AsideRightComponent,BackToTop},
        data(){
          return{
              myBackToTopStyle: {
                right: '50px',
                bottom: '50px',
                width: '40px',
                height: '40px',
                borderRadius: '50%',
                'box-shadow': '0 4px 10px rgba(0,0,0,.05)', 
                lineHeight: '45px', // 请保持与高度一致以垂直居中
                background: '#ffffff'// 按钮的背景颜色
              }
          }
        },
        methods: {

          
        },
        mounted(){
        }
    }
</script>
 
<style >
    .el-card__body{
      padding: 0px;
      background: none;
    }
    .content-body{
    padding: 90px;
    box-sizing: border-box;
    }
   .el-footer {
    background-color: #B3C0D1;
    color: #fff;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    text-align: center;
    line-height: 200px;
    margin-right: 3%;
  }
  
  .el-main {
    background-color: #eee;
    color: #333;
    text-align: center;
    line-height: 160px;
    padding: 0;
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
</style>
 