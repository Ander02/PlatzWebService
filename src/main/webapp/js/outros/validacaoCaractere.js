/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 $(function(){
  var regex = new RegExp('[^ 0-9a-zA-ZàèìòùáéíóúâêîôûãõçÇ~´`?! \b-]', 'g');
  // repare a flag "g" de global, para substituir todas as ocorrências
  $('input').bind('input', function(){
    $(this).val($(this).val().replace(regex, ''));
  });
})