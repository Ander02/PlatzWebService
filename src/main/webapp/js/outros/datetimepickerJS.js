/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$.datetimepicker.setLocale('pt');

$('#datetimepicker-start').datetimepicker({
    lang: 'pt',
    formatTime: 'H:i',
    format: 'd/m/Y H:i',
    formatDate: 'd/m/Y',
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    minDate: 0,
    roundTime: 'ceil',
    onChangeDateTime: function (dp, $input) {
        startDate = $("#datetimepicker-start").val();
    }

});
$("#datetimepicker-end").datetimepicker({
    lang: 'pt',
    formatTime: 'H:i',
    format: 'd/m/Y H:i',
    formatDate: 'd/m/Y',
    minDate: 0 ,
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    onClose: function (current_time, $input) {
        var endDate = $("#datetimepicker-end").val();
      
        if (startDate >= endDate) {
            $('#datetimepicker-end').css({'border': '1px solid red', 'background-color': '#ef9a9a ', 'color': '#fff'});
            $('#p-alerta').css({'display': 'block'});
        } else
        {
            $('#datetimepicker-end').css({'background-color': '#fff', 'border': 'none', 'color': '#000', 'border-bottom': '1px solid #ccc'});
            $('#p-alerta').css({'display': 'none'});
        }
    }
});
$('#datetimepicker-start-edit').datetimepicker({
    language: 'pt',
    formatTime: 'H:i',
    format: 'd/m/Y H:i',
    formatDate: 'd/m/Y',
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    minDate: 0,
    roundTime: 'ceil',
    onChangeDateTime: function (dp, $input) {
        startDate = $("#datetimepicker-start-edit").val();
    }

});
$("#datetimepicker-end-edit").datetimepicker({
    lang: 'pt',
    formatTime: 'H:i',
    format: 'd/m/Y H:i',
    formatDate: 'd/m/Y',
    minDate: 0 ,
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    onClose: function (current_time, $input) {
        var endDate = $("#datetimepicker-end-edit").val();
      
        if (startDate >= endDate) {
            $('#datetimepicker-end-edit').css({'border': '1px solid red', 'background-color': '#ef9a9a ', 'color': '#fff'});
            $('#p-alerta-edit').css({'display': 'block'});
        } else
        {
            $('#datetimepicker-end-edit').css({'background-color': '#fff', 'border': 'none', 'color': '#000', 'border-bottom': '1px solid #ccc'});
            $('#p-alerta-edit').css({'display': 'none'});
        }
    }
});

