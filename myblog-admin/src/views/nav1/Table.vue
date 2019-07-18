<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="标题"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getArticleList">查询</el-button>
				</el-form-item>
				<!-- <el-form-item>
					<el-button type="primary" @click="editArticleDialog = true">新增</el-button>
				</el-form-item> -->
			</el-form>
		</el-col>

		<!--列表-->
			<el-table 
				:data="articleList"
				highlight-current-row 
				style="width: 100%"
				v-loading="listLoading"
				@selection-change="selsChange" 
			>
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="title" label="标题" width="250" sortable>
			</el-table-column>
			<el-table-column prop="content" label="内容" width="430"  sortable>
			</el-table-column>
			<el-table-column prop="categoryName" label="分类" width="100" sortable>
			</el-table-column>
			<el-table-column prop="uploadTime" label="时间" width="200" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination background layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!-- 编辑弹窗 -->
		<el-dialog
			title="编辑"
			:visible.sync="editArticleDialog"
			width="90%"
			:before-close="handleClose">
			<el-input v-model="mdTitle" placeholder="请输入标题"></el-input>
				<mavon-editor :ishljs = "true" v-model="article.mdValue" ></mavon-editor> 
			<span slot="footer" class="dialog-footer">
				<el-button @click="editArticleDialog = false">取 消</el-button>
				<el-button type="primary" @click="editArticle()">确 定</el-button>
			</span>
		</el-dialog>

	</section>
</template>

<script>
	import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';
	import { deleteArticle,getArticleAll ,getArticleOne,updateArticle} from '../../api/article';
	export default {
		data() {
			return {
				mdTitle:'',
				article:{
					mdValue:'',
				},
				editArticleDialog:false,
				filters: {
					name: ''
				},
				articleList: [],
				total: 0,
				pageNum: 1,
				listLoading: false,
				sels: [],//列表选中列

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					id: 0,
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				}

			}
		},
		methods: {
			// 修改文章确定按钮
			editArticle(){
				this.editArticleDialog = false;
				console.log(this.article.mdValue );	
				this.article.title = this.mdTitle;
				updateArticle(this.article).then(result =>{
					this.commonTips(result);
				})
			},
			// 编辑框关闭时去除内容
			handleClose(done) {
				done();
				this.article.mdValue  = ''
				this.mdTitle = ''
			},
			handleCurrentChange(val) {
				console.log("--->",val)
				this.pageNum = val;
				this.getArticleList();
			},
			//获取文章列表
			getArticleList() {
				let para = {
					pageNum: this.pageNum,
					searchKey: this.filters.name,
					pageSize: 10
				};
				this.listLoading = true;
				//NProgress.start();
				getArticleAll(para).then((res) => {
					console.log("--->",res)
					//NProgress.done();
					if(res.data.code == 200){
					this.articleList =res.data.data.list;
					this.total = res.data.data.total
					this.listLoading = false;
					}
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { id: row.id };
					 console.log(row)
					deleteArticle(para).then(result =>{
						this.commonTips(result);
						this.listLoading = false;
					})
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				console.log("==>",row)
				this.editArticleDialog = true;
				getArticleOne({'id':row.id}).then(result =>{
					console.log("根据id查询的文章",result.data)
					this.article = result.data.data
					this.article.mdValue  = result.data.data.markdownContent
					this.mdTitle = result.data.data.title
					console.log("--->",this.article)
				})
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				};
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
						this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getArticleList();
					});
				}).catch(() => {

				});
			},
			// 通用的消息提示
			commonTips(result){
				if(result.data.code == 200){
					this.$message({
						message:result.data.message,
						type:"success"
					})
					this.handleCurrentChange(this.pageNum);
				}else if(result.data.code >= 500){
					this.$message({
						message:result.data.message,
						type:"error"
					})
				}else{
					this.$message({
						message:"请先登陆",
						type:"error"
					})
				}
			},
		},
		mounted() {
			this.getArticleList();
		}
	}

</script>

<style >
.el-dialog{
	margin-top: 7vh !important;
}
.cell{
	overflow: hidden !important;
	white-space: nowrap !important;
	text-overflow: ellipsis !important;
}
</style>