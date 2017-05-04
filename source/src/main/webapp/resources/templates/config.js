    <%if(list.length>0) { %>
        <h3>输出所有数据，共有<%=list.length%>个元素</h3>
        <ul>
            <!-- 循环语句 for-->
            <%for(var i=0; i < list.length; i++){%>
                <li><%=list[i].name%> <a href="###" onclick="javascirpt:deleteConfig(<%=list[i].id%>)">删除<a/></li>
            <%}%>
        </ul>
    <%}else{%>
        <h2>没有数据</h2>   
    <%}%>