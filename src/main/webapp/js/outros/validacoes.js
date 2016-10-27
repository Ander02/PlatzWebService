$(document).ready(function(){
$('#conta-empresa-telefone1').mask('(00) 0000-00009');
$('#conta-empresa-telefone2').mask('(00) 0000-00009');
$('#conta-empresa-cnpj').mask('00.000.000/0000-00', {reverse: true});
$('#empresa-cep').mask('00000-000');

$('#conta-usuario-cpf').mask('000.000.000-00', {reverse: true});

var SPMaskBehavior = function (val) {
  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
},
spOptions = {
  onKeyPress: function(val, e, field, options) {
      field.mask(SPMaskBehavior.apply({}, arguments), options);
    }
};

$('#conta-usuario-telefone').mask(SPMaskBehavior, spOptions);
$('#usuario-cep').mask('00000-000');
});
