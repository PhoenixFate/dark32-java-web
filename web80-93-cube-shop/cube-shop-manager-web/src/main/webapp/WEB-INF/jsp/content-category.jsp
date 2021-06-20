<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">
    </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		url : './content/category/list',
		animate: true,
		method : "GET",
		onContextMenu: function(e,node){
		    // e为右键的点击事件
            e.preventDefault();
            // 下面一行 点击某个节点后显示 节点 的选中效果
            $(this).tree('select',node.target);

            //右键显示的菜单面板
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        onAfterEdit : function(node){
        	var _tree = $(this);
        	// id为0的时候表示为新增的节点
        	if(node.id == 0){
        		// 新增节点
                // {parentId:node.parentId,name:node.text}：虽然格式是json，但向服务器提交但时候并不是json 格式，而是parentId=xxx&name=xxx的格式
        		$.post("./content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
                            //更新id
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else {
        	    //否则为修改的节点
                $.post("./content/category/update",{id:node.id,name:node.text});
            }
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
	    console.log(item)
        console.log(node)
        if(node.children && node.children.length>0){
            $.messager.alert("操作提示", "请删除所有的子节点后删除父亲节点！");
        }else {
            $.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
            	if(r){
            		$.post("./content/category/delete/",{id:node.id},function(){
            			tree.tree("remove",node.target);
            		});
            	}
            });
        }
	}
}
</script>