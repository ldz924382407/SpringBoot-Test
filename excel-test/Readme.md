# excel相关操作
## 1.读取excel文件
```java
<!--读取excel文件-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>easyexcel</artifactId>
    <version>${easyexcel.version}</version>
</dependency>
```

## 2.操作Excel
```java
<!--操作Excel-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>${poi.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>${poi.ooxml.version}</version>
</dependency>
```

## 3.poi和poi-ooxml不同版本时，样式设置小细节会不一样
注意点：
    1）setFillForegroundColor用于设置前景颜色，而setFillPattern用于设置单元格填充样式
    2）setFillPattern必须设置否则光设置setFillForegroundColor无效
    3）setFillPattern，3.10.1版本支持short类型参数，而3.17版本支持FillPatternType类型参数，举例：
        3.10.1版本：cellStyle.setFillPattern((short) 1);
        3.17版本：cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    4）单元格合并请使用：sheet.addMergedRegion(new CellRangeAddress(起始行号, 终止行号, 起始列号, 终止列号)); 原则：左闭右闭

## 4.实现调接口返回另存为窗口保存文件
要求：后端controller直接返回流文件形式即可

注意点1：
    如果只要求浏览器或者postman调后端接口返回另存为窗口，那么直接编写后端代码即可
    如果前端有页面情况下通过ajax形式，那么直接调用接口情况下返回流形式，前端F12只会展示内容，不会有另存为窗口，所以前端必须编写代码才行出现另存为窗口（45行-53行必须有），代码如下

```java
this.$axios.post('/lte/ems/userManage/export', params, config).then((response) => {
    const link = document.createElement('a');
    const blob = new Blob([response.data], { type: 'application/octet-stream' });
    link.style.display = 'none';
    link.href = URL.createObjectURL(blob);
    const fileName = "export_information_" + Date.parse(new Date()) + ".xls";
    link.setAttribute('download', fileName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    this.$loading.hideLoadding();
    this.$refs.downloadDialog.clear();
    this.$refs.downloadDialog.close();
});
```
