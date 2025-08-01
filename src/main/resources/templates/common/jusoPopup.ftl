<#-- templates/common/jusoPopup.ftl -->
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>주소 검색 팝업</title>
<script>
function init() {
console.log("▶ init() called, inputYn =", "${inputYn}");
  <#if inputYn != "Y">
    console.log("→ 최초 호출, API 폼 제출");
    document.form.confmKey.value   = "${jusoApiKey?js_string}";
    document.form.returnUrl.value  = "${returnUrl?js_string}";
    document.form.resultType.value = "4";
    document.form.action           = "https://business.juso.go.kr/addrlink/addrLinkUrl.do";
    document.form.submit();

  <#else>
    console.log("→ 콜백 분기, 파라미터:",
      "${roadFullAddr!}" , "${zipNo!}"
    );
    try {
opener.jusoCallBack(
"${roadFullAddr!?js_string}",
"${roadAddrPart1!?js_string}",
"${addrDetail!?js_string}",
"${roadAddrPart2!?js_string}",
"${engAddr!?js_string}",
"${jibunAddr!?js_string}",
"${zipNo!?js_string}"
);
console.log("✔ opener.jusoCallBack 호출 성공");
} catch (e) {
console.error("✖ callback 호출 실패:", e);
}
    window.close();
  </#if>
}
</script>


</head>
<body onload="init()">
  <form name="form" method="post">
    <input type="hidden" name="confmKey"   value=""/>
    <input type="hidden" name="returnUrl"  value=""/>
    <input type="hidden" name="resultType" value=""/>
    <input type="hidden" name="inputYn"    value="Y"/>
  </form>
</body>
</html>
