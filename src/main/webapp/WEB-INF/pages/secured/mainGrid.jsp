<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<!-- <script type="text/javascript" src="../js/jquery/jquery-1.4.4.min.js"></script> -->
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery/ui-lightness/jquery-ui-1.8.6.custom.css"/>"/>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js"/>"></script>
<script type="text/javascript">
    var jq = jQuery.noConflict();
</script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.6.custom.min.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.locale-en.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js"/>"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<%@ include file="headerInclude.jsp"%>
<script type="text/javascript">
	jq(function() {
		jq("#grid").jqGrid({
		   	url:'<c:url value='/secured/users1'/>',
			datatype: 'json',
			mtype: 'GET',
			colNames:['User Name','First Name','Last Name','Email Id','Active','User Roles','Logged In'],
            colModel:[
                {name:'username',index:'userName', width:100, editable:true, editrules:{required:true}, editoptions:{size:50}, formoptions:{elmprefix:'*'}},
                {name:'firstname',index:'firstName', width:100, editable:true, editrules:{required:true}, editoptions:{size:50}, formoptions:{elmprefix:'*'}},
                {name:'lastname',index:'lastName', width:100, editable:true, editrules:{required:true}, editoptions:{size:50}, formoptions:{elmprefix:'*'}},
                {name:'emailid',index:'emailid', width:100, editable:true, editrules:{required:true},editoptions:{size:50}, formoptions:{elmprefix:'*'}},
                {name:'isactive',index:'active',width:55,align:'center',editable:true,edittype:"checkbox",editoptions:{value:"Yes:No"}},
                {name:'roles',index:'userroles', width:100, editable:true,edittype: 'select',
                	
                	editoptions: { 
                        dataUrl: '<c:url value='/secured/allroles'/>',
                        buildSelect: function (data) {
                            var response = jQuery.parseJSON(data);
                            
                            //document.write(response.Options[1].DisplayText);
                            //var response = $.parseJSON(retValue.d);
                            var s = '<select>';
                            for (k in response.Options) {
                            	s += '<option value="' + response.Options[k].Value + '">' +response.Options[k].DisplayText+ '</option>';
                                //document.writeln(response.Options[k].DisplayText);
                            }

                            /* if (json && json.length) {
                            	document.write('test');
                                for (var i = 0, l = json.length; i < l; i++) {
                                s += '<option value="' + json.Opions[i].Value + '">' +json.Options[i].DisplayText + '</option>';
                                }
                            } */
                            //document.write(s);
                            return s + "</select>";
                            }                  
                    }
                },   
                {name:'lastlogin',index:'loggedin', width:100, editable:false,sorttype:date,editrules: {edithidden:true}, editoptions:{size:10}},
            ],
		   	postData: { 
			},
			rowNum:20,
		   	rowList:[20,40,60],
		   	height: 200,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'userName',
		    viewrecords: true,
		    sortorder: "asc",
		    caption:"Users",
		    emptyrecords: "Empty records",
		    loadonce: false,
		    loadComplete: function() {
			},
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    }
		});
		jq("#grid").jqGrid('navGrid','#pager',
				{edit:false,add:false,del:false,search:true},
				{ },
		        { },
		        { }, 
				{ 
			    	sopt:['eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew'],
			        closeOnEscape: true, 
			        	multipleSearch: true, 
			         	closeAfterSearch: true }
		);


		
		jq("#grid").navButtonAdd('#pager',
				{ 	caption:"Add", 
					buttonicon:"ui-icon-plus", 
					onClickButton: addRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		jq("#grid").navButtonAdd('#pager',
				{ 	caption:"Edit", 
					buttonicon:"ui-icon-pencil", 
					onClickButton: editRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
		jq("#grid").navButtonAdd('#pager',
			{ 	caption:"Delete", 
				buttonicon:"ui-icon-trash", 
				onClickButton: deleteRow,
				position: "last", 
				title:"", 
				cursor: "pointer"
			} 
		);

		jq("#btnFilter").click(function(){
			jq("#grid").jqGrid('searchGrid',
					{multipleSearch: false, 
						sopt:['eq']}
			);
		});

		// Toolbar Search
		jq("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});

	});
</script>
  

<script type="text/javascript">

function addRow() {

	// Get the currently selected row
    jq("#grid").jqGrid('editGridRow','new',
    		{ 	url: '<c:url value='/secured/addUser'/>', 
					editData: {
			    },
			    recreateForm: true,
			    beforeShowForm: function(form) {
			    },
				closeAfterAdd: true,
				reloadAfterSubmit:false,
				afterSubmit : function(response, postdata) 
				{ 
			        var result = eval('(' + response.responseText + ')');
					var errors = "";
					
			        if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
			        }  else {
			        	jq("#dialog").text('Entry has been added successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});
	                }
			    	// only used for adding new records
			    	var new_id = null;
			    	
					return [result.success, errors, new_id];
				}
    		});

}

function editRow() {
	// Get the currently selected row
	var row = jq("#grid").jqGrid('getGridParam','selrow');
	
	if( row != null ) 
		jq("#grid").jqGrid('editGridRow',row,
			{	url: "/spring-jqgrid-integration/krams/crud/edit", 
				editData: {
		        },
		        recreateForm: true,
		        beforeShowForm: function(form) {
		        },
				closeAfterEdit: true,
				reloadAfterSubmit:false,
				afterSubmit : function(response, postdata) 
				{ 
		            var result = eval('(' + response.responseText + ')');
					var errors = "";
					
		            if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
		            }  else {
		            	jq("#dialog").text('Entry has been edited successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});
	                }
		        	
					return [result.success, errors, null];
				}
			});
	else jq( "#dialogSelectRow" ).dialog();
}

function deleteRow() {
	// Get the currently selected row
    var row = jq("#grid").jqGrid('getGridParam','selrow');

    // A pop-up dialog will appear to confirm the selected action
	if( row != null ) 
		jq("#grid").jqGrid( 'delGridRow', row,
          	{ url: '<c:url value='/secured/deleteUser'/>', 
						recreateForm: true,
			            beforeShowForm: function(form) {
			              //change title
			              jq(".delmsg").replaceWith('<span style="white-space: pre;">' +
			            		  'Delete selected record?' + '</span>');
	            		  
						  //hide arrows
			              jq('#pData').hide();  
			              jq('#nData').hide();  
			            },
          				reloadAfterSubmit:false,
          				closeAfterDelete: true,
          				afterSubmit : function(response, postdata) 
						{ 
			                var result = eval('(' + response.responseText + ')');
							var errors = "";
							
			                if (result.success == false) {
								for (var i = 0; i < result.message.length; i++) {
									errors +=  result.message[i] + "<br/>";
								}
			                }  else {
			                	jq("#dialog").text('Entry has been deleted successfully');
								jq("#dialog").dialog( 
										{	title: 'Success',
											modal: true,
											buttons: {"Ok": function()  {
												jq(this).dialog("close");} 
											}
										});
			                }
		                	// only used for adding new records
		                	var new_id = null;
		                	
							return [result.success, errors, new_id];
						}
          	});
	 else jq( "#dialogSelectRow" ).dialog();
}

</script>  
  
<p>Application Users</p>
<div id="jqgrid">
	<table id="grid"></table>
	<div id="pager"></div>
</div>
<div id="placeholder"></div>
<div id="dialog" title="Feature not supported" style="display:none">
	<p>That feature is not supported.</p>
</div>

<div id="dialogSelectRow" title="Warning" style="display:none">
	<p>Please select row</p>
</div>

</body>

</html>

