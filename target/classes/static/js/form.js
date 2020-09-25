 $(".input-select-indicator").click(function(){
         $(".input-select-dropdown").hide();
         
         $(this).toggleClass("opened");
         if( $(this).hasClass("opened"))
         {
             $(this).next(".input-select-dropdown").show();

         }
         else
         {
            $(this).next(".input-select-dropdown").hide();
         }
           
            });



 $("[data-gtm-id='search-searchpanel-input-checkbox-1414597193348']").click(function(){

if($("[data-gtm-id='search-searchpanel-input-checkbox-1414597193348']:checked").length == 0)
{
$(this).closest(".main-search-form-item.input-select").find(".input-select-indicator").html("all");
}

   else
   {
      $(this).closest(".main-search-form-item.input-select").find(".input-select-indicator").html("selected");
   }



});
 $("a.main-search-form-switch-trigger.collapsible-trigger").click(function(){
$("#main-search-form-extended").slideToggle();
});