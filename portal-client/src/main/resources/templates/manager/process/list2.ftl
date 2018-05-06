<!DOCTYPE html>
<html lang="GB18030">
  <head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> 张三 <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="user.html"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="auth_cert.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_project.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a> 
							</li>
							<li style="height:30px;">
								<a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a> 
							</li>
							<li style="height:30px;">
								<a href="process.html" style="color:red"><i class="glyphicon glyphicon-random"></i> 流程管理</a> 
							</li>
							<li style="height:30px;">
								<a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a> 
							</li>
							<li style="height:30px;">
								<a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a> 
							</li>
							<li style="height:30px;">
								<a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a> 
							</li>
							<li style="height:30px;">
								<a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed" >
						<a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="file" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>

<button type="button" class="btn btn-primary" style="float:right;" onclick="uploadFile()"><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>流程名称</th>
                  <th>流程标识</th>
                  <th>流程版本</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="procDefBody">
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="5" align="center">
						<ul class="pagination">
								<li class="disabled"><a href="#">上一页</a></li>
								<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页</a></li>
							 </ul>
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
    <form id="uploadForm" action="${APP_PATH}/manager/process/upload" method="post" enctype="multipart/form-data">
        <input id="procDefFile" type="file" name="procDefFile">
    </form>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/jquery/jquery.form.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
				
				pageQuery(1);
				
				$("#procDefFile").change(function(){
				    //$("#uploadForm").submit();
				    // 使用AJAX实现文件上传
				    // 上传文件时，数据应该做特殊的处理
				    /*
				    var formData = new FormData($("#uploadForm")[0]);
				    
				    $.ajax({
				         type : "POST",
				         url  : "${APP_PATH}/manager/process/upload",
				         data : formData,
				         contentType:false, // 不对数据进行内容处理
				         processData:false, // 如果要发送 DOM 树信息或其它不希望转换的信息，请设置为 false
				         success : function(result) {
				             if ( result.success ) {
					        	layer.msg("流程定义上传成功。", {time:2000, icon:6}, function(){
					        	    $("#uploadForm")[0].reset();
					        	    pageQuery(1);
					        	});
				             } else {
					        	layer.msg("流程定义上传失败，请重新操作。", {time:2000, icon:5, shift:6}, function(){
					        	});
				             }
				         }
				    });
				    */
				    
				    // 使用form插件提交文件内容
				    $("#uploadForm").ajaxSubmit({
				        beforeSubmit : function() {
				        
				        },
				        resetForm: true,
				        success : function(result) {
				             if ( result.success ) {
					        	layer.msg("流程定义上传成功。", {time:2000, icon:6}, function(){
					        	    pageQuery(1);
					        	});
				             } else {
					        	layer.msg("流程定义上传失败，请重新操作。", {time:2000, icon:5, shift:6}, function(){
					        	});
				             }
				        }
				    });
				});
            });
            
            function pageQuery(pageno) {
            	
            	var loadingIndex = -1;
            	var jsonData = {
           			"pageno" : pageno,
           			"pagesize" : 10
            	}
            	
            	$.ajax({
            		url : "${APP_PATH}/manager/process/pageQuery",
            		type : "POST",
            		data : jsonData,
            		beforeSend : function() {
            			loadingIndex = layer.load(2, {time: 10*1000});
            		},
            		success : function(result) {
            			layer.close(loadingIndex);
            			if(result.success) {
            				var procDefPage = result.data;
            				var procDefs = procDefPage.datas;
            				var procDefRows = "";
            				$.each(procDefs, function(i, procDef){
            	                procDefRows = procDefRows +  '<tr>';
	          	                procDefRows = procDefRows +  '  <td>'+(i+1)+'</td>';
	          	                procDefRows = procDefRows +  '  <td>'+procDef.name+'</td>';
	          	                procDefRows = procDefRows +  '  <td>'+procDef.key+'</td>';
	          	                procDefRows = procDefRows +  '  <td>'+procDef.version+'</td>';
	          	                procDefRows = procDefRows +  '  <td>';
	          					procDefRows = procDefRows +  '      <button type="button" onclick="" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
	          					procDefRows = procDefRows +  '      <button type="button" onclick="" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
	          					procDefRows = procDefRows +  '	  <button type="button" onclick="" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
	          					procDefRows = procDefRows +  '  </td>';
	          	                procDefRows = procDefRows +  '</tr>';
            				});
            				
            				$("#procDefBody").html(procDefRows);
            				/*
            				var pageContent = "";
            				
            				if ( pageno > 1 ) {
            					pageContent += '<li><a onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
            				}
            				for ( var i = 1; i <= userPage.totalno; i++ ) {
            					if ( pageno == i ) {
            						pageContent += '<li class="active"><a href="#">'+i+'</a></li>';
            					} else {
            						pageContent += '<li><a onclick="pageQuery('+i+')">'+i+'</a></li>';
            					}
            					
            				}
            				if ( pageno < userPage.totalno ) {
            					pageContent += '<li><a onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
            				}
            				
            				$(".pagination").html(pageContent);
            				*/
            			}
            		}
            	});
            }
            
            function uploadFile() {
                $("#procDefFile").click();
            }
            
            
        </script>
  </body>
</html>
