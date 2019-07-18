<template>
  <section>
    <NavMenuComponent></NavMenuComponent>
    <el-container class="content-body">
      <AsideComponent></AsideComponent>
      <el-col :md="17">
        <div class="articleDetail animated zoomIn " >
          <div class="markdown-body" ref="hlDiv" v-highlight v-html="mdContent"></div>
        </div>
      </el-col>
    </el-container>
  </section>
</template>
<script>
//1.先使用import导入你要在该组件中使用的子组件
import NavMenuComponent from "@/components/NavMenu.vue";
import AsideComponent from "@/components/Aside.vue";
import { getArticleOne } from "../api/article";
let marked = require("marked");
let hljs = require("highlight.js");
import javascript from "highlight.js/lib/languages/javascript";
import "github-markdown-css";
export default {
  components: { NavMenuComponent, AsideComponent },
  data() {
    return {
        id:"",
        mdValue:"",
        title:''
    };
  },
 
  methods: {
    // 初始化页面时加载对应文章
    initDetail(){
        this.id = this.$route.query.id
        getArticleOne({'id':this.id}).then(result =>{
        this.mdValue = result.data.data.markdownContent
        this.title = result.data.data.title
        })
    },
    initMarked(){
      marked.setOptions({
        renderer: new marked.Renderer(),
        pedantic: false,
        gfm: true,
        tables: true,
        breaks: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        xhtml: false
       });
    },
  },
  mounted() {
    this.initDetail();
    this.initMarked();
  },
  computed:{
    mdContent(){
        return marked(this.mdValue,{ sanitize: true })
    }
}
};
</script>

<style>
.el-footer {
  background-color: #ffffff !important;
}
.articleDetail {
  background: #ffffff;
  text-align: left;
  margin-left: 20px;
  border-radius: 4px;
  -webkit-box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}
.articleDetail .markdown-body {
  padding: 0 15px;
  font-size: 15px;
}
.content-body {
  padding: 90px;
  box-sizing: border-box;
}
.hljs,
.hljs-tag,
.hljs-subst {
  color: #24292e !important;
}
.el-col-md-6 {
  width: 24%;
}
</style>
