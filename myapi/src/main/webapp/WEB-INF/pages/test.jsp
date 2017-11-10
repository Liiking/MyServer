<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试日期选择</title>
</head>
<body>

<input type="text" id="pubDate" name="pubDate" />
<script src="${pageContext.request.contextPath}/laydate/laydate.js" charset="UTF-8"></script>
<script>
    //执行一个laydate实例
    laydate.render({
        elem: '#pubDate' //指定元素
    });
</script>
</body>
</html>