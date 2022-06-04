

$(() => {
	
	
	
  $(".page-item").click(function () {
	  let pg = $(this).attr("data-pg");
	  const address = JSON.parse(localStorage.getItem("address"));
	  let poll = `
		  <input type="hidden" name="sido" id="sido" value="${address.sido}" />
		  <input type="hidden" name="gugun" id="gugun" value="${address.gugun}" />
		  <input type="hidden" name="dong" id="dong" value="${address.dong}" />
		  <input type="hidden" name="pg" id="pg" value="${pg}" />
	  `
	  
     	// $("#pg").val($(this).attr("data-pg"));
	  	$("#pageForm").append(poll);
	  	$("#pageForm").action = "/apt/search"
     	$("#pageForm").submit();
  });

  $("#word-btn").click(function () {
	// let pg = $(this).attr("data-pg");
	  const address = JSON.parse(localStorage.getItem("address"));
	  let poll = `
		  <input type="hidden" name="sido" id="sido" value="${address.sido}" />
		  <input type="hidden" name="gugun" id="gugun" value="${address.gugun}" />
		  <input type="hidden" name="dong" id="dong" value="${address.dong}" />
	  `
     	// $("#pg").val($(this).attr("data-pg"));
	  	$("#word-form").append(poll);
     	$("#word-form").submit();
  });


// 이메일 api시작하기
  (function(){ 
    emailjs.init("uPrm0nkV2Rv9Li7gd"); 
  })();

  
  
  $("#search-btn").on("click", () => {
	 let sido = $("#sido").val();
	 let gugun = $("#gugun").val();
	 let dong = $("#dong").val();
	 
	 const address = {
			 sido: sido,
			 gugun: gugun,
			 dong: dong,
	 };
	 localStorage.setItem("address", JSON.stringify(address));
    
// location.href = "/locationSearch.html";
  });

  
   
  
});




