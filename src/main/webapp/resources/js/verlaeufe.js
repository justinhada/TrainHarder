$(document).ready(function(){
	$('#ueberschrift-verlauf-anzeige').click(function(){
		setzeInhalteZurueck();
		$('#verlauf-anzeige').show();
		setzeAktiveUeberschrift($(this));
	});

	$('#ueberschrift-verlauf-import').click(function(){
		setzeInhalteZurueck();
		$('#verlauf-import').show();
		setzeAktiveUeberschrift($(this));
	});

	$('#ueberschrift-verlauf-export').click(function(){
		setzeInhalteZurueck();
		$('#verlauf-export').show();
		setzeAktiveUeberschrift($(this));
	});
})

function setzeInhalteZurueck(){
		$('.div-verlauf').hide();
		
		$('.ueberschrift-verlauf').css('background-color', '#17356C');
		$('.ueberschrift-verlauf').css('color', 'white');
		$('.ueberschrift-verlauf').css('cursor', 'pointer');
	}

function setzeAktiveUeberschrift(ueberschrift){
	ueberschrift.css('background-color', 'white');
	ueberschrift.css('color', 'black');
	ueberschrift.css('cursor', 'text');
}