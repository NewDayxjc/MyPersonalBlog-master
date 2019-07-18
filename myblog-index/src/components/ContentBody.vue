<template>
  <el-col :md="13" class="contentBody" style="margin-left:20px;">
    <div v-infinite-scroll="loadMore" infinite-scroll-disabled="disabled">
      <div v-for="(item, index) in contentDate" :key="item.index">
        <div @click="goToDetail(item.id)">
          <el-card
            @mouseover.native="mouseOver(index)"
            @mouseleave.native="mouseLeave(index)"
            :style="item.active"
            class="infinite-list-item content-card animated fadeInUp"
            shadow="hover"
          >
            <div class="content-item">
              <el-image
                class="content-image"
                :src="item.imgUrl ?item.imgUrl:url"
                draggable="false"
                :style="item.rotate"
              ></el-image>
              <div
                class="content-right"
                :style="{'color': item.active ? 'white !important' : 'black'}"
              >
                <div class="content text-overflow">
                  <span class="content-title">{{item.title}}</span>
                </div>
                <div class="content content-overflow">
                  <span class="font-style">{{item.content}}</span>
                </div>
                <div class="admin-time">
                  <span
                    class="font-style"
                    :style="{'color': item.active ? 'white !important' : 'black'}"
                  >{{item.uploadTime}}</span>
                  <span
                    class="font-style"
                    :style="{'color': item.active ? 'white !important' : 'black'}"
                  >
                    <i class="el-icon-view">{{item.view}}</i>
                  </span>
                </div>
                <div class="content-type">
                  <span>{{item.categoryName}}</span>
                </div>
              </div>
            </div>
          </el-card>
        </div>
      </div>
      <p v-if="loading">加载中...</p>
      <p v-if="noMore">没有更多了</p>
    </div>
  </el-col>
</template>
<script>
import Swiper from "swiper";
import { getArticleAll } from "../api/article";
export default {
  data() {
    return {
      param: {
        pageSize: 4,
        pageNum: 1,
        isOffset: 0,
        isFirst: true
      },
      contentDate: [],
      loading: false,
      url: "http://39.96.94.165:8888/group1/M00/00/00/rBADI1z7c46AV2boAABFfkxD5d8233.png",
      status: 0
    };
  },
  methods: {
    // 初始化轮播计数
    initArticleCount() {
      this.contentDate.countNum = 0;
    },
    getArticleList() {
      getArticleAll(this.param).then(result => {
        result.data.data.list.map((obj, index) => {
          this.InContentDate(obj);
          if (!this.status) {
            // this.status = 0
            obj.rotate = null;
            obj.active = null;
            obj.fontColor = null;

            obj.imgUrl = ""; // 初始化
            var str = obj.markdownContent;
            let arr =str.match(/!\[(.*?)\]\((.*?)\)/);
            // console.log(arr);
            if (arr) {
              obj.imgUrl = arr[2]; // 获取第一张图片做标题图片
            } else {
              obj.imgUrl = 0;
            }
            this.contentDate.push(obj);
            // console.log("-->",this.contentDate)
          }
        });
        this.contentDate.total = result.data.data.total;
        this.contentDate.countNum += result.data.data.list.length;
      });
    },
    goToDetail(id) {
      this.$router.push({
        path: "/detail?id=" + id
      });
    },
    loadMore() {
      if(!this.param.isFirst){
        this.param.isOffset = this.param.pageSize++
        this.loading = true;

        setTimeout(() => {
          this.getArticleList();
          this.loading = false;
        }, 1000);
      }else{
          this.param.isFirst = false
      }
    },
    initSwiper() {
      if (this.swiper != null) return;
      this.swiper = new Swiper(".swiper-container", {
        loop: false,
        speed: 1000,
        autoplay: {
          delay: 3000, //3秒切换一次
          disableOnInteraction: false
        },
        observer: true, //修改swiper自己或子元素时，自动初始化swiper
        observeParents: true, //修改swiper的父元素时，自动初始化swiper

        pagination: {
          el: ".swiper-pagination"
          // dynamicBullets: true,
        }
      });
    },
    mouseOver(n) {
      // transition: transform 0.3s;
      // transform:rotate(5deg);
      this.contentDate[n].rotate =
        "transition: transform 0.3s;transform:rotate(5deg); ";
      this.contentDate[n].active =
        "cursor: pointer;color: #ffffff;background: #475669 !important;transition: background 0.3s;z-index:999";
    },
    mouseLeave(n) {
      this.contentDate[n].active = null;
      this.contentDate[n].rotate = "transition: transform 0.3s;";
    },
    InContentDate(n) {
      this.contentDate.forEach(result => {
        if (result.id == n.id) {
          this.status = 1;
        }else{
          this.status = 0;
        }
      });
    }
  },
  mounted() {
    // this.initSwiper();
    this.getArticleList();
    this.initArticleCount();
  },
  computed: {
    noMore() {
      return this.contentDate.countNum >= this.contentDate.total;
    },
    disabled() {
      return this.loading || this.noMore;
    }
  },
  watch: {}
};
</script>

<style scoped>
.swiper-wrapper img:hover {
  transition: all 0.3s;
  transform: scale(1.1);
}
.swiper-wrapper img {
  transition: all 0.3s;
  width: 100%;
  max-height: 270px !important;
}
.swiper-container {
  height: 270px;
  border-radius: 4px;
  margin: 0 auto;
  width: 100% !important;
}
.swiper-slide {
  width: 100% !important;
}
.content-type span {
  float: right;
  background: rgb(31, 137, 243);
  color: #ffffff;
  font-size: 12px;
  border-radius: 4px;
  padding: 1px;
}
.admin-time {
  text-align: left;
  letter-spacing: 0.3px;
  padding-left: 10px;
  float: left;
}
.font-style {
  letter-spacing: 0.3px;
  /* color: rgba(0, 0, 0, 0.300); */
  font-size: 10px;
}
.content-overflow {
  max-width: 533px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
}
.content-right {
  display: flex;
  flex-flow: column;
}
.text-overflow span {
  max-height: 25px;
  min-height: 25px;
}

.text-overflow {
  width: 500px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.content-item {
  display: flex;
  overflow: hidden;
  max-height: 170px;
}
.content-item .content {
  padding: 10px 10px 0px 10px;
  text-align: left;
}

.content-item .el-image {
  padding: 10px 10px 10px 10px;
  min-width: 150px;
  min-height: 150px;
  max-width: 150px;
  max-height: 150px;
  overflow: hidden;
  border-radius: 40px;
}
.content-image img {
  transition: transform 0.3s;
}

.content-card {
  /* margin: 10px 0px; */
  margin-bottom: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
  border-radius: 6px;
  webkit-touch-callout: none; /* iOS Safari */

  -webkit-user-select: none; /* Chrome/Safari/Opera */

  -khtml-user-select: none; /* Konqueror */

  -moz-user-select: none; /* Firefox */

  -ms-user-select: none; /* Internet Explorer/Edge */

  user-select: none; /* Non-prefixed version, currently*/
}

.el-card__body {
  background: none !important;
}

.el-carousel--horizontal {
  border-radius: 4px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>