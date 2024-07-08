function MiClase(elId, elNombre) {
    var attr = this
    this.id = elId;
    this.nombre = elNombre;
    // this.prototype.cont++
    // this.muestraId = function () {
    //     console.log("El ID del objeto es " + this.id);
    // }
    // this.ponNombre = function (nom) {
    //     this.nombre = nom.toUpperCase();
    // }
    this.muestraId = function () {
        console.log("El ID del objeto es " + attr.id);
    }
}
MiClase.prototype.cont = 0
MiClase.prototype.muestraId = function () {
    console.log("El ID del objeto es " + this.id);
}
MiClase.prototype.ponNombre = function (nom) {
    this.nombre = nom.toUpperCase();
}

class Persona {
    constructor(id, nombre) {
        this.id = id
        this.nombre = nombre
    }
    muestraId() {
        console.log("El ID del objeto es " + this.id);
    }
    ponNombre(nom) {
        this.nombre = nom.toUpperCase();
    }
}
