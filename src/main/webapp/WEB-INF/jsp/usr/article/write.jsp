<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Write" />
<%@ include file="../common/toastUi.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doWrite" onsubmit="submitForm(this); return false;" method="GET">
				<input type="hidden" name="body"/>
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>게시판</th>
								<td>
									<label>
										<input type="radio" name="boardId" value="1"/>
										&nbsp;공지사항
									</label>
									&nbsp;
									<label>
										<input type="radio" name="boardId" value="2" checked/>
										&nbsp;자유
									</label>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="title" placeholder="제목을 입력해주세요." /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
										<script type="text/x-template"></script>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-active btn-neutral" >글쓰기</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div>
				<button class="btn btn-active btn-neutral" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
<script>
function getUriParams(uri) {
	  uri = uri.trim();
	  uri = uri.replaceAll('&amp;', '&');
	  if ( uri.indexOf('#') !== -1 ) {
	    let pos = uri.indexOf('#');
	    uri = uri.substr(0, pos);
	  }

	  let params = {};

	  uri.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
	  return params;
	}

	function codepenPlugin() {
	  const toHTMLRenderers = {
	    codepen(node) {
	      const html = renderCodepen(node.literal);

	      return [
	        { type: 'openTag', tagName: 'div', outerNewLine: true },
	        { type: 'html', content: html },
	        { type: 'closeTag', tagName: 'div', outerNewLine: true }
	      ];
	    }
	  }
	  
	  function renderCodepen(uri) {    
	    let uriParams = getUriParams(uri);

	    let height = 400;

	    let preview = '';

	    if ( uriParams.height ) {
	      height = uriParams.height;
	    }

	    let width = '100%';

	    if ( uriParams.width ) {
	      width = uriParams.width;
	    }

	    if ( !isNaN(width) ) {
	      width += 'px';
	    }

	    let iframeUri = uri;

	    if ( iframeUri.indexOf('#') !== -1 ) {
	      let pos = iframeUri.indexOf('#');
	      iframeUri = iframeUri.substr(0, pos);
	    }

	    return '<iframe height="' + height + '" style="width: ' + width + ';" scrolling="no" title="" src="' + iframeUri + '" frameborder="no" allowtransparency="true" allowfullscreen="true"></iframe>';
	  }

	  return { toHTMLRenderers }
	}

	function replPlugin() {
	  const toHTMLRenderers = {
	    repl(node) {
	      const html = renderRepl(node.literal);

	      return [
	        { type: 'openTag', tagName: 'div', outerNewLine: true },
	        { type: 'html', content: html },
	        { type: 'closeTag', tagName: 'div', outerNewLine: true }
	      ];
	    }
	  }
	  
	  function renderRepl(uri) {
	    var uriParams = getUriParams(uri);

	    var height = 400;

	    if ( uriParams.height ) {
	      height = uriParams.height;
	    }

	    return '<iframe frameborder="0" width="100%" height="' + height +'px" src="' + uri + '"></iframe>';
	  }

	  return { toHTMLRenderers }
	}

	function youtubePlugin() {
	  const toHTMLRenderers = {
	    youtube(node) {
	      const html = renderYoutube(node.literal);

	      return [
	        { type: 'openTag', tagName: 'div', outerNewLine: true },
	        { type: 'html', content: html },
	        { type: 'closeTag', tagName: 'div', outerNewLine: true }
	      ];
	    }
	  }

	  function renderYoutube(uri) {
	    uri = uri.replace('https://www.youtube.com/watch?v=', '');
	    uri = uri.replace('http://www.youtube.com/watch?v=', '');
	    uri = uri.replace('www.youtube.com/watch?v=', '');
	    uri = uri.replace('youtube.com/watch?v=', '');
	    uri = uri.replace('https://youtu.be/', '');
	    uri = uri.replace('http://youtu.be/', '');
	    uri = uri.replace('youtu.be/', '');

	    let uriParams = getUriParams(uri);

	    let width = '100%';
	    let height = '100%';

	    let maxWidth = 500;

	    if ( !uriParams['max-width'] && uriParams['ratio'] == '9/16' ) {
	      uriParams['max-width'] = 300;
	    }

	    if ( uriParams['max-width'] ) {
	      maxWidth = uriParams['max-width'];
	    }

	    let ratio = '16/9';

	    if ( uriParams['ratio'] ) {
	      ratio = uriParams['ratio'];
	    }

	    let marginLeft = 'auto';

	    if ( uriParams['margin-left'] ) {
	      marginLeft = uriParams['margin-left'];
	    }

	    let marginRight = 'auto';

	    if ( uriParams['margin-right'] ) {
	      marginRight = uriParams['margin-right'];
	    }

	    let youtubeId = uri;

	    if ( youtubeId.indexOf('?') !== -1 ) {
	      let pos = uri.indexOf('?');
	      youtubeId = youtubeId.substr(0, pos);
	    }

	    return '<div style="max-width:' + maxWidth + 'px; margin-left:' + marginLeft + '; margin-right:' + marginRight + ';" class="ratio-' + ratio + ' relative"><iframe class="absolute top-0 left-0 w-full" width="' + width + '" height="' + height + '" src="https://www.youtube.com/embed/' + youtubeId + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>';
	  }

	  return { toHTMLRenderers }
	}

	function katexPlugin() {
	  const toHTMLRenderers = {
	    katex(node) {
	      let html = katex.renderToString(node.literal, {
	        throwOnError: false
	      });

	      return [
	        { type: 'openTag', tagName: 'div', outerNewLine: true },
	        { type: 'html', content: html },
	        { type: 'closeTag', tagName: 'div', outerNewLine: true }
	      ];
	    },
	  }

	  return { toHTMLRenderers }
	}

	const ToastEditor__chartOptions = {
	  minWidth: 100,
	  maxWidth: 600,
	  minHeight: 100,
	  maxHeight: 300
	};

	function ToastEditor__init() {
	  $('.toast-ui-editor').each(function(index, node) {
	    const $node = $(node);
	    const $initialValueEl = $node.find(' > script');
	    const initialValue = $initialValueEl.length == 0 ? '' : $initialValueEl.html().trim();

	    const editor = new toastui.Editor({
	      el: node,
	      previewStyle: 'tab',
	      initialValue: initialValue,
	      height:'600px',
	      plugins: [
	        [toastui.Editor.plugin.chart, ToastEditor__chartOptions],
	        [toastui.Editor.plugin.codeSyntaxHighlight, {highlighter:Prism}],
	        toastui.Editor.plugin.colorSyntax,
	        toastui.Editor.plugin.tableMergedCell,
	        toastui.Editor.plugin.uml,
	        katexPlugin,
	        youtubePlugin,
	        codepenPlugin,
	        replPlugin
	      ],
	      customHTMLSanitizer: html => {
	        return DOMPurify.sanitize(html, { ADD_TAGS: ["iframe"], ADD_ATTR: ['width', 'height', 'allow', 'allowfullscreen', 'frameborder', 'scrolling', 'style', 'title', 'loading', 'allowtransparency'] }) || ''
	      }
	    });

	    $node.data('data-toast-editor', editor);
	  });
	}

	function ToastEditorView__init() {
	  $('.toast-ui-viewer').each(function(index, node) {
	    const $node = $(node);
	    const $initialValueEl = $node.find(' > script');
	    const initialValue = $initialValueEl.length == 0 ? '' : $initialValueEl.html().trim();
	    $node.empty();

	    let viewer = new toastui.Editor.factory({
	      el: node,
	      initialValue: initialValue,
	      viewer:true,
	      plugins: [
	        [toastui.Editor.plugin.chart, ToastEditor__chartOptions],
	        [toastui.Editor.plugin.codeSyntaxHighlight, {highlighter:Prism}],
	        toastui.Editor.plugin.colorSyntax,
	        toastui.Editor.plugin.tableMergedCell,
	        toastui.Editor.plugin.uml,
	        katexPlugin,
	        youtubePlugin,
	        codepenPlugin,
	        replPlugin
	      ],
	      customHTMLSanitizer: html => {
	        return DOMPurify.sanitize(html, { ADD_TAGS: ["iframe"], ADD_ATTR: ['width', 'height', 'allow', 'allowfullscreen', 'frameborder', 'scrolling', 'style', 'title', 'loading', 'allowtransparency'] }) || ''
	      }
	    });

	    $node.data('data-toast-editor', viewer);
	  });
	}

	$(function() {
	  ToastEditor__init();
	  ToastEditorView__init();
	});

	function submitForm(form){
	  form.title.value = form.title.value.trim();
	  
	  if(form.title.value.length == 0){
	    alert('제목을 입력해주세요');
	    form.title.focus();
	    return;
	  }
	  
	  const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
	  const markdown = editor.getMarkdown().trim();
	  console.log(markdown);
	  
	  if(markdown.length == 0){
	    alert('내용을 입력해주세요');
	    editor.focus();
	    return;
	  }
	  
	  
	  form.body.value = markdown;
	  
	  form.submit();
	  
	}
</script>
<%@ include file="../common/footer.jsp" %>