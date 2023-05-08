注意说明：
- Spring版本：5.3.22
- SpringBoot版本：2.7.3  
- Swagger版本：3.0.0

# 一、简介
一般我们在对接前后端的时候，都需要提供相应的接口文档。对于后端来说，编写接口文档即费时费力，还会经常因为没有及时更新，导致前端对接时出现实际接口与文档不一致。而且手写接口文档还容易出错，而swagger很好的解决了这个痛点。

Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。可用于：1.接口的文档在线自动生成、2.功能测试。

# 二、Swagger 的优点
- 号称时最流行的 API 框架
- 接口文档在线生成，避免同步的麻烦
- 可以支持在线对接口执行测试
- 支持多语言

# 三、注解说明
@Api : 
    位置：用在请求的类上，例如Controller，
    作用：说明该类的主要作用。
    参数说明：@Api("tags":"", "value":"", "description ":"")
        tags：说明该类的作用，参数是个数组，可以填多个。
        value="该参数没什么意义，在UI界面上不显示，所以不用配置"
        description = "用户基本信息操作"

@ApiOperation：
    位置：用在请求方法上
    作用：说明方法的用途、作用
    参数说明：@ApiOperation("value":"", "notes":"", "tags ":"")
        value="方法的用途和作用"
        notes="方法的注意事项和备注"			
        tags：说明该方法的作用， 参数是个数组，可以填多个。例如格式：tags={"作用1","作用2"}

@ApiImplicitParams: 
    位置：用在请求方法上
    作用：包含多@ApiImplicitParam，包含一组参数说明。
    参数说明：@ApiImplicitParams({@ApiImplicitParam("name":"", "value":"", "dataType":"", "paramType":"", "required":"")...})
@ApiImplicitParam：
    位置：用在请求方法上
    作用：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面说明
    参数说明：@ApiImplicitParam("name":"", "value":"", "dataType":"", "paramType":"", "required":"")
        name="参数名称"
        value="参数说明"
        dataType="数据类型"，默认String
        required=true 参数是否必选
        paramType="query" 表示参数放在哪里，查询参数类型，这里有几种形式：
            header --> 请求参数的获取：@RequestHeader，参数在 request headers 里边提交
            query --> 请求参数的获取：@RequestParam，直接跟参数，完成自动映射赋值
            path（用于 restful 接口）--> 请求参数的获取：@PathVariable，以地址的形式提交数据
            body（不常用）--> 以流的形式提交 仅支持 POST
            form（不常用）--> 以 form 表单的形式提交 仅支持 POST


@ApiResponses：用在请求的方法上，表示一组响应
@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
    code：数字，例如400
    message：信息，例如"请求参数没填好"
    response：抛出异常的类

@ApiModel：
    位置：用在返回对象类上
    作用：描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候）
    参数说明：@ApiModel("description":"")
        description="描述实体的作用"
@ApiModelProperty：
    位置：用在属性上
    作用：描述一个model的属性
    参数说明：@ApiModelProperty("value":"", "name":"", "required":"")
        value="参数说明" 描述参数的意义
        name="参数名称" 参数的变量名
        required=true 参数是否必选

@ApiParam：
    位置：用在请求方法、参数上
    作用：对参数使用说明（如：说明 或是否必填等）
    参数说明：@ApiParam("value":"", "name":"", "required":"")
        value="参数说明" 描述参数的意义
        name="参数名称" 参数的变量名
        required=true 参数是否必选

@ApiIgnore:
    位置：用在类，方法，参数
    作用：表示这个方法或者类被忽略，也就是在http://ip:port/swagger-ui.html上不显示
    参数说明：无

# 四、注意事项说明
1.@ApiOperation注解的tags属性尽量少用，因为tags属性会对接口重新分类，导致登录swagger-ui查看接口不清晰，具体如图，比如我只有一个Controller里面包含4个方法，按常理应该只有一个”用户管理“的接口说明，如果我使用tags属性，那么下方会多出一个tags属性标识过的重复的的接口说明（说白了就是有重复的显示，感觉不直观）。
2.SwaggerConfig中无论是Docket属性还是ApiInfoBuilder属性、以及注解中属性都有很多，该案例只使用常用的几个属性，想了解其他属性请查看其他人博客了解即可。
3.Swagger2.x版本中开启Swagger在SpringBoot项目的启动类或配置类中添加@EnableSwagger2，而Swagger3.0.0版本中开启Swagger在SpringBoot项目的启动类或配置类中添加@EnableOpenApi
4.ApiInfo 基本信息参数介绍，可以看到，它提供了 8 个可以配置属性，根据名字也能猜出其中的意思。
    version：API 版本
    title：文档标题
    description：文档描述
    termsOfServiceUrl：团队链接
    license：许可
    licenseUrl：许可链接
    contact：联系人信息
    vendorExtensions：扩展信息
5.问题：注解@ApiParam和@ApiImplicitParams都是作用在方法上，区别是啥？
答案：他两互相都可以互为替代品，即描述参数用@ApiParam和@ApiImplicitParams都可以，只不过@ApiImplicitParams要和@ApiImplicitParam搭配使用。
