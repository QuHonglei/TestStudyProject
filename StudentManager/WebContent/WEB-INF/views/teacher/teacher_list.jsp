<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>导员列表</title>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	var gradeList = ${gradeListJson};
	$(function() {	
		var table;
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'导员列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"get_list?t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rowusernames:true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},  
 		        <c:if test="${userType == 1 || userType == 3}">  
 		        {field:'username',title:'导员编号',width:100, sortable: true},
 		        </c:if>    
 		        {field:'name',title:'姓名',width:100},
 		        {field:'sex',title:'性别',width:50},
 		        <c:if test="${userType == 1}">
 		        {field:'password',title:'密码',width:150},
 		        </c:if>
 		        {field:'phone',title:'联系方式',width:150},
 		        {field:'gradeId',title:'所属院系',width:200, 
					//遍历院系id并转换为院系名称
 		        	formatter:function(value,index,row){
 		        		for(var i=0;i<gradeList.length;i++){
 		        			if(gradeList[i].id == value){
 		        				return gradeList[i].name;
 		        			}
 		        		}
 		        		return value;
 		    	   }
 		       	},
	 		]], 
	        toolbar: "#toolbar"
	    }); 
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    //修改
	    $("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    //删除
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var usernames = [];
            	$(selectRows).each(function(i, row){
            		usernames[i] = row.username;
            	});
            	var ids = [];
            	$(selectRows).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "如果指导导员下存在学生信息则无法删除，须先删除指导导员下属的学生信息，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "delete",
							data: {ids: ids},
							dataType:'json',
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒",data.msg,"warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	  	
	  	//下拉框通用属性
	  	$("#add_gradeList, #edit_gradeList, #add_gradeList, #edit_gradeList").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //可多选
	  		editable: false, //不可编辑
	  		method: "post",
	  	});
	  	
	  	//设置添加导员窗口
	    $("#addDialog").dialog({
	    	title: "添加导员",
	    	width: 400,
	    	height: 350,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: true,
	    	minimizable: false,
	    	maximizable: true,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var data = $("#addForm").serialize();
							$.ajax({
								type: "post",
								url: "add",
								data: data,
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_username").textbox('setValue', "");
										$("#add_name").textbox('setValue', "");
										$("#add_password").textbox('setValue', "");
										$("#add_sex").textbox('setValue', "男");
										$("#add_phone").textbox('setValue', "");
										
										
										//重新刷新页面数据
										
							  			$('#dataList').datagrid("reload");
										
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						$("#add_username").textbox('setValue', "");
						$("#add_name").textbox('setValue', "");
						$("#add_password").textbox('setValue', "");
						$("#add_phone").textbox('setValue', "");
						//重新加载年级
						$("#add_gradeList").combobox("clear");
						$("#add_gradeList").combobox("reload");
					}
				},
			],
			onClose: function(){
				$("#add_username").textbox('setValue', "");
				$("#add_password").textbox('setValue', "");
			}
	    });
	  	
	  	//设置编辑导员窗口
	    $("#editDialog").dialog({
	    	title: "修改导员信息",
	    	width: 400,
	    	height: 400,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: true,
	    	minimizable: false,
	    	maximizable: true,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var data = $("#editForm").serialize();
							$.ajax({
								type: "post",
								url: "edit",
								data: data,
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","更新成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");
										
										$("#gradeList").combobox('setValue', gradeid);
							  			
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						//清空表单
						$("#edit_name").textbox('setValue', "");
						$("#edit_sex").textbox('setValue', "男");
						$("#edit_phone").textbox('setValue', "");
						$("#edit_password").textbox('setValue', "");
						$("#edit_gradeList").combobox("clear");
						$("#edit_gradeList").combobox("reload");
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit-id").val(selectRow.id);
				$("#edit_username").textbox('setValue', selectRow.username);
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_sex").textbox('setValue', selectRow.sex);
				$("#edit_phone").textbox('setValue', selectRow.phone);
				$("#edit_password").textbox('setValue', selectRow.password);
				$("#edit_gradeId").combobox('setValue', selectRow.gradeId);
				
			}
	    });
	    //搜索按钮
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('reload',{
	  			name:$("#search-name").textbox('getValue'),
	  			gradeId:$("#search-grade-id").combobox('getValue')
	  		});
	  	});
	   
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	
	<div id="toolbar">
	
		<c:if test="${userType == 1}">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
		</c:if>	
		<div>
			<c:if test="${userType == 1 || userType == 3}">
			<a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			</c:if>
			<c:if test="${userType == 1}">
			<a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
			</c:if>
			<c:if test="${userType == 1 || userType == 2}">
			导员姓名：<input id="search-name" class="easyui-textbox" />
			所属院系：
			<select id="search-grade-id" class="easyui-combobox" style="width: 150px;">
				<option value="">全部</option>
				<c:forEach items="${ gradeList}" var="grade">
	    			<option value="${grade.id }">${grade.name }</option>
	    		</c:forEach>
			</select>
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
			</c:if>
		</div>
		
	</div>
	
	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">  
   		<form id="addForm" method="post">
   		<!-- 隐藏域传值，将密码默认设置为111111 -->
   		<input type="hidden" name="password" id="add-password" value="111111">
	    	<table id="addTable" cellpadding="8">
	    		<tr >
	    			<td>导员编号:</td>
	    			<td>
	    				<input id="add_username"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="username" data-options="required:true, missingMessage:'请填写导员编号'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>导员姓名:</td>
	    			<td>
	    				<input id="add_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="name" data-options="required:true, missingMessage:'请填写导员姓名'"  />
	    			</td>
	    		</tr>
	    		<!--
	    		<tr >
	    			<td>登录密码:</td>
	    			<td>
	    				<input id="add_password"  class="easyui-textbox" style="width: 200px; height: 30px;" type="password" name="password" validType="password" data-options="required:true, missingMessage:'请填写登录密码'"  />
	    			</td>
	    		</tr>
	    		-->
	    		<tr >
	    			<td>所属院系:</td>
	    			<td>
	    				<select id="add_gradeId"  class="easyui-combobox" style="width: 200px;" name="gradeId" data-options="required:true, missingMessage:'请选择所属院系'">
	    					<c:forEach items="${ gradeList}" var="grade">
	    						<option value="${grade.id }">${grade.name }</option>
	    					</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>导员性别:</td>
	    			<td>
	    				<select id="add_sex"  class="easyui-combobox" style="width: 200px;" name="sex" data-options="required:true, missingMessage:'请选择导员性别'">
	    					<option value="男">男</option>
	    					<option value="女">女</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>联系方式:</td>
	    			<td>
	    				<input id="add_phone"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="phone" data-options="required:true, missingMessage:'请填写联系方式'"  />
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	
	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">
   		<form id="editForm" method="post">
	    	<input type="hidden" name="id" id="edit-id">
	    	<table id="editTable" cellpadding="8">
	    		<tr >
	    			<td>导员编号:</td>
	    			<td>
	    				<input id="edit_username"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="username" data-options="required:true, missingMessage:'请填写导员编号'"  />
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>导员姓名:</td>
	    			<td>
	    				<input id="edit_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="name" data-options="required:true, missingMessage:'请填写导员姓名'"  />
	    			</td>
	    		</tr>
	    		<c:if test="${userType == 1}">
	    		<tr >
	    			<td>登录密码:</td>
	    			<td>
	    				<input id="edit_password"  class="easyui-textbox" style="width: 200px; height: 30px;" type="password" name="password" validType="password" data-options="required:true, missingMessage:'请填写登录密码'"  />
	    			</td>
	    		</tr>
	    		</c:if>
	    		<tr >
	    			<td>所属院系:</td>
	    			<td>
	    				<select id="edit_gradeId"  class="easyui-combobox" style="width: 200px;" name="gradeId" data-options="required:true, missingMessage:'请选择所属院系'">
	    					<c:forEach items="${ gradeList}" var="grade">
	    						<option value="${grade.id }">${grade.name }</option>
	    					</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>导员性别:</td>
	    			<td>
	    				<select id="edit_sex"  class="easyui-combobox" style="width: 200px;" name="sex" data-options="required:true, missingMessage:'请选择导员性别'">
	    					<option value="男">男</option>
	    					<option value="女">女</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr >
	    			<td>联系方式:</td>
	    			<td>
	    				<input id="edit_phone"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="phone" data-options="required:true, missingMessage:'请填写联系方式'"  />
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>  	
</body>
</html>