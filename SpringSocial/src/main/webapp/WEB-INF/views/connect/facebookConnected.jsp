<html>
 <head>
  <title>Connected to Facebook</title>
  
  <script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '422677131276236',
      xfbml      : true,
      version    : 'v2.5'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
 </head>
 <body>
  <h3>Connected to Facebook</h3>
  <p>You are now connected to your Facebook account.Click <a href="/">Here</a> to continue
  </p>
  <div
  class="fb-like"
  data-share="true"
  data-width="450"
  data-show-faces="true">
</div>
 </body>
</html>