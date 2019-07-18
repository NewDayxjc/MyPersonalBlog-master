<template>
  <section>
    <div class="choose-bar">
      <div style="margin-right:20px;">
       <el-input placeholder="请输入标题" v-model="article.title" clearable  style="width:500px" ></el-input>
      </div>
      <div>
        <!-- {{categoryId}} -->
        <el-select
          v-model="categoryId"
          @change="barStatue(1)"
          clearable
          filterable
          placeholder="所属栏目"
        >
          <el-option
            v-for="item in categoryList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </div>

      <div style="margin-left:20px;">
        <el-checkbox v-model="checked" @change="barStatue()" label="归档" border></el-checkbox>
      </div>

    </div>
    <div class="markdown-style">
      <mavon-editor
        :ishljs="true"
        v-model="mdValue"
        ref="md"
        :toolbars="toolbars"
        @imgAdd="$imgAdd"
      ></mavon-editor>
      <div style="float:right;margin-top:30px;">
        <el-button type="primary" @click="submit()">发布</el-button>
      </div>
    </div>
  </section>
</template>
<script>
import { getCategoryList } from "../../api/categoryApi";
import { uploadFile, uploadByBase64 } from "../../api/uploadFileApi";
import { add } from "../../api/article";

export default {
  data() {
    return {
      mdValue: "",
      checked: false,
      // markdown 配置信息
      toolbars: {
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: false, // 有序列表
        ul: false, // 无序列表

        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: false, // 沉浸式阅读
        htmlcode: true, // 展示html源码

        /* 1.3.5 */

        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐

        preview: true // 预览
      },
      categoryList: [
        {
          id: null,
          name: null
        }
      ],
      categoryId: null,
      article: {
        title: null,
        markdownContent: null,
        cateId: null
      }
    };
  },
  methods: {
    init() {
      getCategoryList().then(result => {
        console.log(result);
        this.categoryList = result.data.data;
      });
    },
    barStatue(n) {
      if (n) {
        this.checked = false;
      } else {
        this.categoryId = null;
      }
    },
    submit() {
      if ((this.categoryId != null || this.checked) && this.mdValue != "") {
          this.article.markdownContent = this.mdValue
          this.article.cateId = this.categoryId
          add(this.article).then(result => {
            if (result.data.code == 500){
              this.$message({
                message: result.data.message,
                type: "error"
              });
            } 
            else if (result.data.code == 200){
              this.$message({
              message: "发布成功",
              type: "success"
            });
            }
          });
          } else {
            this.$message({
              message: "信息不完整",
              type: "error"
            });
      }
    },
    $imgAdd(pos, $file) {
      
      uploadByBase64($file.miniurl).then(result => {
        console.log("===>",result.data)
        if (result.data.code == 204){
          this.$message({
            message: "截图上传成功",
            type: "success"
          });
          this.$refs.md.$img2Url(pos, result.data.data);
        }else if(result.data==""){
            this.$message({
            message: "请先登录",
            type: "error"
          });
        }else{
            this.$message({
            message: "上传图片失败",
            type: "error"
          });
        }

      });

    }
  },
  mounted() {
    this.init();
  }
};
</script>
<style>
.choose-bar {
  margin-top: 10px;
  display: flex;
}
.markdown-style {
  margin-top: 10px;
}
.markdown-body {
  height: 65vh;
}
.v-note-wrapper .v-note-op.shadow {
  border: none;
  /* box-shadow: 0 0px 4px rgba(0,0,0,0.157), 0 0px 4px rgba(0,0,0,0.227); */
  box-shadow: none !important;
  border: 1px solid #eee !important;
}
.v-note-wrapper .v-note-panel.shadow {
  border: none;
  /* box-shadow: 0 0px 3px rgba(0,0,0,0.157), 0 0px 3px rgba(0,0,0,0.227); */
  box-shadow: none !important;
  border: 1px solid #eee !important;
}
</style>