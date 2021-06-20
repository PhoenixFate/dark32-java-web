<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>student</title>
    <style>
        .border {
            border: 1px solid black;
        }
        .red {
            background-color: orangered;
        }
        .green {
            background-color: #1a8532;
        }
    </style>
</head>
<body>
    <table class="border">
        <h2>学生信息：</h2>
        <tr>
            <th>序号</th>
            <th>学生id</th>
            <th>学生姓名</th>
            <th>学生年龄</th>
            <th>学生住址</th>
        </tr>
        <#list studentList as student>
            <#if student_index %2 ==0>
                <tr class="red">
            <#else >
                <tr class="green">
            </#if>
                    <td>序号:&nbsp;&nbsp;${student_index}</td>
                    <td>学生id:&nbsp;&nbsp;${student.id}</td>
                    <td>学生姓名:${student.name}</td>
                    <td>学生年龄:${student.age}</td>
                    <td>学生住址:${student.address}</td>
                </tr>
        </#list>
    </table>
    <br>
    <hr>
    当前日期：${today?date}<br>
    当前日期：${today?time}<br>
    当前日期：${today?datetime}<br>
    当前日期：${today?string("yyyy-MM-dd HH:mm:ss")}<br>
    <hr>
    null值的处理： ${myNull!"this value is null"} <br>
    <hr>
    <#if myNull??>
        判断是否为空；是为空
    <#else>
        判断是否为空；不为空
    </#if>
    <br>
    <hr>
    引用模板测试：<br>
    <#include "hello.ftl">
    <br>
</body>
</html>