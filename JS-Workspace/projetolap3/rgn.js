/*     Rede Geodésica Nacional

Aluno 1: 57498, Vicente Maria Morais Pinto Rodrigues dos Santos
Aluno 2: 57609, Joao Pedro Serra Campos Soares 

Comentario:

O ficheiro "rng.js" tem de incluir, logo nas primeiras linhas,
um comentário inicial contendo: o nome e número dos dois alunos que
realizaram o projeto; indicação de quais as partes do trabalho que
foram feitas e das que não foram feitas (para facilitar uma correção
sem enganos); ainda possivelmente alertando para alguns aspetos da
implementação que possam ser menos óbvios para o avaliador.

0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789

HTML DOM documentation: https://www.w3schools.com/js/js_htmldom.asp
Leaflet documentation: https://leafletjs.com/reference-1.7.1.html
*/



/* GLOBAL CONSTANTS */

const MAP_CENTRE =
	[38.661,-9.2044];  // FCT coordinates
const MAP_ID =
	"mapid";
const MAP_ATTRIBUTION =
	'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> '
	+ 'contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>';
const MAP_URL =
	'https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token='
	+ 'pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw'
const MAP_ERROR =
	"https://upload.wikimedia.org/wikipedia/commons/e/e0/SNice.svg";
const MAP_LAYERS =
	["streets-v11", "outdoors-v11", "light-v10", "dark-v10", "satellite-v9",
		"satellite-streets-v11", "navigation-day-v1", "navigation-night-v1"]
const RESOURCES_DIR =
	"resources/";
const VG_ORDERS =
	["order1", "order2", "order3", "order4"];
const RGN_FILE_NAME =
	"rgn.xml";




/* GLOBAL VARIABLES */

let map = null;

let vgsGroups = [];
let altCircles;
let circlesFromPopups;
let visibleVGs = [];

let allvgs = [];

/* USEFUL FUNCTIONS */

// Capitalize the first letter of a string.
function capitalize(str)
{
	return str.length > 0
			? str[0].toUpperCase() + str.slice(1)
			: str;
}

// Distance in km between to pairs of coordinates over the earth's surface.
// https://en.wikipedia.org/wiki/Haversine_formula
function haversine(lat1, lon1, lat2, lon2)
{
    function toRad(deg) { return deg * 3.1415926535898 / 180.0; }
    let dLat = toRad(lat2 - lat1), dLon = toRad (lon2 - lon1);
    let sa = Math.sin(dLat / 2.0), so = Math.sin(dLon / 2.0);
    let a = sa * sa + so * so * Math.cos(toRad(lat1)) * Math.cos(toRad(lat2));
    return 6372.8 * 2.0 * Math.asin (Math.sqrt(a))
}

function loadXMLDoc(filename)
{
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", filename, false);
	try {
		xhttp.send();
	}
	catch(err) {
		alert("Could not access the local geocaching database via AJAX.\n"
			+ "Therefore, no POIs will be visible.\n");
	}
	return xhttp.responseXML;	
}

function getAllValuesByTagName(xml, name)  {
	return xml.getElementsByTagName(name);
}

function getFirstValueByTagName(xml, name)  {
	return getAllValuesByTagName(xml, name)[0].childNodes[0].nodeValue;
}


/* POI */

class POI
{
	constructor(name, latitude, longitude)
	{
		this.name = name;
		this.latitude = parseFloat(latitude);
		this.longitude = parseFloat(longitude);
	}
}

class VG extends POI
{
	
	constructor(name, latitude, longitude, altitude, type)
	{
		super(name, latitude, longitude);
		this.altitude = parseFloat(altitude);
		this.type = type;
	}
}

class VG1 extends VG
{
	constructor(name, latitude, longitude, altitude, type)
	{
		super(name, latitude, longitude, altitude, type);
		this.order = 1;
		this.limitlow = 30;
		this.limithigh = 60;
	}

	getMarker(icons) {
		let typeforbutton = this.type.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/\//g, '');
		return L.marker([this.latitude, this.longitude, this.altitude], {icon: icons['order' + this.order]})
				.bindPopup("<b>" + this.name + "</b>" +
							"<br>" + this.type +
							"<br> Order " + this.order +
							"<br> Long/ " + this.longitude +
							"<br> Alt/ " + this.altitude +
							"<br> Lat/ " + this.latitude +
							"<br> VGs de ordem1 proximos/ " + this.getCloseOrder1VGs() +
							"<br> <input type=\"button\" id=\"same_order\" value =\"Mostrar mesmo tipo\" onclick =\"drawSameType(\'"+ typeforbutton + "\')\">" +
							"<br> <input type=\"button\" id=\"maps\" value =\"Abrir no Google Maps\" onclick =\"openMaps(" + this.latitude + ")\">")
							.bindTooltip("I'm the marker of VG <b>" + this.name + "</b>.");
	}
	getCloseOrder1VGs(){
		let counter = 0;
		if (allvgs.length>0){
			let arrayofVgs = allvgs[0];
			for (let i = 0; i< arrayofVgs.length; i++){
				let dist = haversine(this.latitude, this.longitude, arrayofVgs[i].latitude, arrayofVgs[i].longitude);
				if (dist<=this.limithigh){
					counter++;
				}
			}
			return counter -1;
		} else return 0;
	}
}

class VG2 extends VG
{
	constructor(name, latitude, longitude, altitude, type)
	{
		super(name, latitude, longitude, altitude, type);
		this.order = 2;
		this.limitlow = 20;
		this.limithigh = 30;
	}
	getMarker(icons) {
		let typeforbutton = this.type.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/\//g, '');
		return L.marker([this.latitude, this.longitude, this.altitude], {icon: icons['order' + this.order]})
				.bindPopup("<b>" + this.name + "</b>" +
							"<br>" + this.type +
							"<br> Order " + this.order +
							"<br> Long/ " + this.longitude +
							"<br> Alt/ " + this.altitude +
							"<br> Lat/ " + this.latitude +
							"<br> <input type=\"button\" id=\"same_order\" value =\"Mostrar mesmo tipo\" onclick =\"drawSameType(\'"+ typeforbutton + "\')\">" +
							"<br> <input type=\"button\" id=\"same_order\" value =\"Mostrar mesma ordem a menos de 30km\" onclick =\"drawCloseSameOrder("
							+ this.order + "," + this.limithigh+ ","+ this.latitude + "," + this.longitude+ ")\">" +
							"<br> <input type=\"button\" id=\"maps\" value =\"Abrir no Google Maps\" onclick =\"openMaps(" + this.latitude + ", " + this.longitude + ")\">")
							.bindTooltip("I'm the marker of VG <b>" + this.name + "</b>.");
	}
}

class VG3 extends VG
{
	constructor(name, latitude, longitude, altitude, type)
	{
		super(name, latitude, longitude, altitude, type);
		this.order = 3;
		this.limitlow = 5;
		this.limithigh = 10;
	}
	getMarker(icons) {
		let typeforbutton = this.type.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/\//g, '');
		return L.marker([this.latitude, this.longitude, this.altitude], {icon: icons['order' + this.order]})
				.bindPopup("<b>" + this.name + "</b>" +
							"<br>" + this.type +
							"<br> Order " + this.order +
							"<br> Long/ " + this.longitude +
							"<br> Alt/ " + this.altitude +
							"<br> Lat/ " + this.latitude +
							"<br> <input type=\"button\" id=\"same_order\" value =\"Mostrar mesmo tipo\" onclick =\"drawSameType(\'"+ typeforbutton + "\')\">" +
							"<br> <input type=\"button\" id=\"maps\" value =\"Abrir no Google Maps\" onclick =\"openMaps(" + this.latitude + ")\">")
							.bindTooltip("I'm the marker of VG <b>" + this.name + "</b>.");
	}
}

class VG4 extends VG
{
	constructor(name, latitude, longitude, altitude, type)
	{
		super(name, latitude, longitude, altitude, type);
		this.order = 4;
	}
	getMarker(icons) {
		let typeforbutton = this.type.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/\//g, '');
		return L.marker([this.latitude, this.longitude, this.altitude], {icon: icons['order' + this.order]})
				.bindPopup("<b>" + this.name + "</b>" +
							"<br>" + this.type +
							"<br> Order " + this.order +
							"<br> Long/ " + this.longitude +
							"<br> Alt/ " + this.altitude +
							"<br> Lat/ " + this.latitude +
							"<br> <input type=\"button\" id=\"same_order\" value =\"Mostrar mesmo tipo\" onclick =\"drawSameType(\'"+ typeforbutton + "\')\">"  +
							"<br> <input type=\"button\" id=\"maps\" value =\"Abrir no Google Maps\" onclick =\"openMaps(" + this.latitude + ")\">")
							.bindTooltip("I'm the marker of VG <b>" + this.name + "</b>.");
	}
}

/* MAP */

class Map {
	constructor(center, zoom)
	{
		this.lmap = L.map(MAP_ID).setView(center, zoom);
		this.addBaseLayers(MAP_LAYERS);
		let icons = this.loadIcons(RESOURCES_DIR);
		this.loadRGN(RESOURCES_DIR + RGN_FILE_NAME, icons);
		this.addClickHandler(
			e=>L.popup().setLatLng(e.latlng).setContent("You clicked the map at " + e.latlng.toString())
		);
		this.addCircle(MAP_CENTRE, 100, "FCT/UNL");
	}

	makeMapLayer(name, spec) {
		let urlTemplate = MAP_URL;
		let attr = MAP_ATTRIBUTION;
		let errorTileUrl = MAP_ERROR;
		let layer =
			L.tileLayer(urlTemplate, {
					minZoom: 6,
					maxZoom: 19,
					errorTileUrl: errorTileUrl,
					id: spec,
					tileSize: 512,
					zoomOffset: -1,
					attribution: attr
			});
		return layer;
	}

	addBaseLayers(specs) {
		let baseMaps = [];
		for(let i in specs)
			baseMaps[capitalize(specs[i])] =
				this.makeMapLayer(specs[i], "mapbox/" + specs[i]);
		baseMaps[capitalize(specs[0])].addTo(this.lmap);
		L.control.scale({maxWidth: 150, metric: true, imperial: false})
									.setPosition("topleft").addTo(this.lmap);
		L.control.layers(baseMaps, {}).setPosition("topleft").addTo(this.lmap);
		return baseMaps;
	}

	loadIcons(dir) {
		let icons = [];
		let iconOptions = {
			iconUrl: "??",
			shadowUrl: "??",
			iconSize: [16, 16],
			shadowSize: [16, 16],
			iconAnchor: [8, 8],
			shadowAnchor: [8, 8],
			popupAnchor: [0, -6] // offset the determines where the popup should open
		};

		for(let i = 0 ; i < VG_ORDERS.length ; i ++)
		{
			iconOptions.iconUrl = dir + VG_ORDERS[i] + ".png";
		    icons[VG_ORDERS[i]] = L.icon(iconOptions);
		}
		return icons;
	}

	loadRGN(filename, icons) {
		let vgsO1 = [], vgsO2 = [], vgsO3 = [], vgsO4 = [];
		let xmlDoc = loadXMLDoc(filename);
		let xs = getAllValuesByTagName(xmlDoc, "vg");
		if(xs.length == 0)
		{
			alert("Empty file");
		}
		else
		{
			for(let i = 0; i < xs.length; i ++)
			{
				var name = getFirstValueByTagName(xs[i], "name"),
					latitude = getFirstValueByTagName(xs[i], "latitude"),
					longitude = getFirstValueByTagName(xs[i], "longitude"),
					order = getFirstValueByTagName(xs[i], "order"),
					altitude = getFirstValueByTagName(xs[i], "altitude"),
					type = getFirstValueByTagName(xs[i], "type");

				switch(true)
				{
					case order == 1:
						let vg1 = new VG1(name, latitude, longitude,altitude, type, icons);
						vgsO1.push(vg1); break;
					case order == 2:
						let vg2 = new VG2(name, latitude, longitude, altitude, type, icons);
						vgsO2.push(vg2); break;
					case order == 3:
						let vg3 = new VG3(name, latitude, longitude, altitude, type, icons);
						vgsO3.push(vg3); break;
					case order == 4:
						let vg4 = new VG4(name, latitude, longitude, altitude, type, icons);
						vgsO4.push(vg4); break;
				}
			}
		}
		allvgs = [vgsO1,vgsO2,vgsO3,vgsO4];
		this.addMarkersToGroups(allvgs,icons);
	}

	addMarkersToGroups(arrayvgs,icons){
		let markers = [];
		for (let i = 0; i<arrayvgs.length; i++){
			let arrayOrder = arrayvgs[i];
			markers[i] = [];
			for (let j = 0; j<arrayOrder.length; j++){
				markers[i].push(arrayOrder[j].getMarker(icons));
			}
		}

		for (let w = 0; w<markers.length; w++){
			vgsGroups.push(L.layerGroup(markers[w]));
		}
	}

	addClickHandler(handler) {
		let m = this.lmap;
		function handler2(e) {
			hideCircles();
			return handler(e).openOn(m);
		}
		return this.lmap.on('click', handler2);
	}

	addCircle(pos, radius, popup) {
		let circle = L.circle(pos, radius, {color: 'red', fillColor: 'pink', fillOpacity: 0.4});
		circle
			.bindPopup(popup)
				.bindTooltip(popup)
					.addTo(this.lmap);
		return circle;
	}

	showGroup(order)
	{
		vgsGroups[order-1].addTo(this.lmap);
	}

	hideGroup(order)
	{
		vgsGroups[order-1].remove(this.lmap);
	}
}

/* FUNCTIONS for HTML */



function onLoad()
{
	altCircles = L.layerGroup();
	circlesFromPopups = L.layerGroup();
	map = new Map(MAP_CENTRE, 14);
}

function arrayUnion(arr1, arr2){
	for (let i = 0; i<arr2.length; i++){
		arr1.push(arr2[i])
	}
}

function visibleVGSCount()
{
	return visibleVGs.length;
}

function visibleVGsUpdate()
{
	visibleVGs = [];
	for(let i = 0; i < VG_ORDERS.length; i ++)
	{
		if(document.getElementById(VG_ORDERS[i]).checked)
		{
			arrayUnion(visibleVGs,allvgs[i]);
		}
	}
	
	return visibleVGs;
}

// Dada uma ordem imprime o numero de vgs visiveis com essa ordem
function visibleVGSWithOrder(order)
{
	let visibleVGSorder = [];
	if(document.getElementById(VG_ORDERS[order - 1]).checked)
	{
		visibleVGSorder = allvgs[order-1];
	}
	return visibleVGSorder;
}

// Devolve o menos elevado dos vgs visiveis
function get_lowest_VG(){
	let vgs = visibleVGs;
	let min = -1;
	let lowest;
	for (i = 0; i<vgs.length; i++){	
		if (vgs[i].altitude < min || min == -1){
				min = vgs[i].altitude;
				lowest = vgs[i];
		}
	}
	if(lowest == null){
		return ["",0];
	}
	
	return [lowest.name, min];
}

// Devolve o mais elevado dos vgs visiveis
function get_highest_VG(){
	let vgs = visibleVGs;
	let max = 0;
	let highest;
	
	for (i = 0; i<vgs.length; i++){
		if (vgs[i].altitude > max){
				max = vgs[i].altitude;
				highest = vgs[i];
		}
	}
	if(highest == null){
		return ["",0];
	}
	
	return [highest.name, max];
}

function checkboxUpdate(id)
{
	/*No enunciado diz apenas para fazer desaparecer os circulos após
	  o utilizador clicar no mapa mas preferimos também removelos quando é feita
	  uma alteração nas checksBoxes e dos vgs visiveis
	*/
	hideCircles();
	visibleVGsUpdate();
	var order = id.match(/\d+/);
	if(document.getElementById(id).checked) 
  	{
  		map.showGroup(order);
  	}
  	else
  	{
  		map.hideGroup(order);
  	}
  	document.getElementById("visible_vgs_order".concat(order)).textContent = visibleVGSWithOrder(order).length;
  	document.getElementById("visible_vgs").textContent = visibleVGSCount();
	document.getElementById("highest_vg").textContent = get_highest_VG()[0];
	document.getElementById("highest_vg_alt").textContent = get_highest_VG()[1] + " m";
	document.getElementById("lowest_vg").textContent = get_lowest_VG()[0];
	document.getElementById("lowest_vg_alt").textContent = get_lowest_VG()[1] + " m";
}

function getDistanceBetweenVGs(vg1,vg2){
	return haversine(vg1.latitude,vg1.longitude,vg2.latitude,vg2.longitude);
}

function vgsfuncionalidade4(){
	let vgsdr = []; //vgs que desrespeitam os limites das distancias
	let vgs = [];

	for (let k = 0; k<VG_ORDERS.length; k++){
		vgs.push(visibleVGSWithOrder(k+1));
	}
	
	for (let i = 0; i < vgs.length; i++){
		let vgsSameOrder = vgs[i];
		if (vgsSameOrder.length>0){
			let low = vgsSameOrder[0].limitlow;
			let high = vgsSameOrder[0].limithigh;
			if(low != null && high != null){
				for (let j = 0; j<vgsSameOrder.length;j++){
					let respect = false;
					for (let n = 0; n<vgsSameOrder.length;n++){
						let dist = getDistanceBetweenVGs(vgsSameOrder[j],vgsSameOrder[n]);
						if (dist >= low && dist<=high){
							respect = true;
						}
						n++;
					}
					if(!respect){
						vgsdr.push(vgsSameOrder[j].name + " ---> ordem " + vgsSameOrder[j].order);
					}
				}
			}
		}

	}
	
	let res = "VGs que não respeitam as distâncias previstas:\n";
	if(vgsdr.length == 0){
		res = "Nada a mostrar"
	}else{
		for(let k = 0; k < vgsdr.length; k++){
			res = res + "\n" + vgsdr[k];
		}
	}
	return res;
}

function hideCircles(){
	map.lmap.removeLayer(altCircles);
	map.lmap.removeLayer(circlesFromPopups);
}

function drawSameType(type){
	
	hideCircles();
	let vgs = visibleVGs;
	circlesFromPopups = L.featureGroup();
	for (i = 0; i<vgs.length; i++){
		let typeforbutton = vgs[i].type.normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/\//g, '');
		if (typeforbutton == type){
			let vglat = vgs[i].latitude;
			let vglng =vgs[i].longitude;
			let circle = L.circle([vglat,vglng], 500,
				{color: 'blue', fillColor: 'blue', fillOpacity: 0.3});
			circle.addTo(circlesFromPopups);
		}
	}
	map.lmap.addLayer(circlesFromPopups);
}

function drawCloseSameOrder(order,limit,lat,lng){
	hideCircles();
	let vgs = vgsGroups[order-1].getLayers();
	circlesFromPopups = L.featureGroup();
	for (i = 0; i<vgs.length; i++){
		let vglat = vgs[i]._latlng.lat;
		let vglng =vgs[i]._latlng.lng;
		let dist = haversine(lat,lng,vglat,vglng);
		if(dist <= limit && dist != 0){
			let circle = L.circle([vglat,vglng], 500,
				{color: 'yellow', fillColor: 'yellow', fillOpacity: 0.3});
			circle.addTo(circlesFromPopups);
		}
	}
	map.lmap.addLayer(circlesFromPopups);
}

function openMaps(latitude, longitude) {
	window.open("http://maps.google.com/maps?q=&layer=c&cbll=".concat(latitude).concat(",").concat(longitude));
}

function drawAltitudes(){
	map.lmap.removeLayer(altCircles);
	visibleVGsUpdate();
	let vgs = visibleVGs;
	altCircles = L.featureGroup();
	
	for (i = 0; i<vgs.length; i++){
		if(vgs[i] != null){
			let vglat = vgs[i].latitude;
			let vglng =vgs[i].longitude;
			let vgalt = vgs[i].altitude;
			let vgname = vgs[i].name;
			if (!isNaN(vgalt)){
				let circle = L.circle([vglat,vglng], vgalt*3,
					{color: 'red', fillColor: 'pink', fillOpacity: 0.4});
				circle.bindPopup(vgname).bindTooltip(vgname);
				circle.addTo(altCircles);
			}
		}
	}
	map.lmap.addLayer(altCircles);

}
