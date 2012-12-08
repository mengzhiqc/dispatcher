<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
	<title><decorator:title default="Aifang Task Dispatch System"/></title>
	<link href="<s:url value='/bootstrap/css/bootstrap.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <script language="JavaScript" type="text/javascript" src="<s:url value='/js/jquery.js'/>"></script>	
    <decorator:head/>
</head>
<body>	
	<!-- <div class="navbar navbar-fixed-top navbar-inverse">
            <div class='navbar-inner'>
                <div class="container">
                    <a class="brand" href="/">DISPATCHER</a>
                    <div class="nav-collapse">
                        <ul class="nav">
							<li class="active"><a href="/">首页</a></li>                           
							<li class="divider-vertical"></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle">任务</a>
                                <ul class="dropdown-menu">
                                  <li><a href="/project/">My Task</a></li>
                                  <li><a href="/webgit/index">Latest Week</a></li>
                                </ul>
                            </li>
                       </ul>
                    </div>
                    <div class="nav-collapse">
                    	<ul class="nav pull-right">
                           <li><a href='/login'>[ Login ]</a></li>
			        	</ul>
                	</div>
                </div>
            </div>
        </div> -->
        

       
		<div class='container'>
			<ul class="nav nav-tabs">
  				<li class="active">
    				<a href="#">Home</a>
  				</li>
  				<li><a href="#">任务列表</a></li>
  				<li><a href="#">近期任务</a></li>
			</ul>
			<decorator:body />
        </div>

        <footer>
            <div class="container">
                       <div class="row" style="text-align: center;">
                			<div class="span12" title="Any fool can write code that a computer can understand. Good programmers write code that humans can understand.">&copy; Aifang Sys
                			</div>
            		</div>
            </div>
        </footer>

         <div class="modal hide" id="aifangModal" style="display:none;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h3 id="modal-header">提示</h3>
            </div>
            <div class="modal-body" id="modal-body"></div>
            <div class="modal-footer" id="modal-footer">
                <a id="modal-footer-close" href="#" class="btn" data-dismiss="modal">TIPS</a>
            </div>
        </div>
        <script src="/dispatcher/bootstrap/js/bootstrap-modal.js"></script>
        <script src="/dispatcher/bootstrap/js/bootstrap-dropdown.js"></script> 
    
</body>
</html>
