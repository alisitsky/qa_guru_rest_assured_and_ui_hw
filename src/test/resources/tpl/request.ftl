<html>
<#-- @ftlvariable name="in.reqres.data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
<head>
    <meta http-equiv="content-type" content="text/html; charset = UTF-8">
    <script src="https://yastatic.net/jquery/2.2.3/jquery.min.js" crossorigin="anonymous"></script>

    <link href="https://yastatic.net/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://yastatic.net/bootstrap/3.3.6/js/bootstrap.min.js" crossorigin="anonymous"></script>

    <link type="text/css" href="https://yandex.st/highlightjs/8.0/styles/github.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/highlight.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/bash.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/json.min.js"></script>
    <script type="text/javascript" src="https://yandex.st/highlightjs/8.0/languages/xml.min.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<div>
    <pre><code><#if in.reqres.data.method??>${in.reqres.data.method}<#else>GET</#if>: <#if in.reqres.data.url??>${in.reqres.data.url}<#else>Unknown</#if></code></pre>
</div>

<#if in.reqres.data.body??>
    <h4>Body</h4>
    <div>
        <pre><code>${in.reqres.data.body}</code></pre>
    </div>
</#if>

<#if (in.reqres.data.headers)?has_content>
    <h4>Headers</h4>
    <div>
        <#list in.reqres.data.headers as name, value>
            <div>
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>


<#if (in.reqres.data.cookies)?has_content>
    <h4>Cookies</h4>
    <div>
        <#list in.reqres.data.cookies as name, value>
            <div>
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>

<#if in.reqres.data.curl??>
    <h4>Curl</h4>
    <div>
        <pre><code>${in.reqres.data.curl}</code></pre>
    </div>
</#if>
</body>
</html>