var o1 = new MiClase("99", "Objeto de prueba");
var o2 = new MiClase("6", "Otro de prueba");
o1 = new Persona("99", "Objeto de prueba");
o2 = new Persona("6", "Otro de prueba");
console.log(o1.muestraId == o2.muestraId)
o1.muestraId()
o2.muestraId()
var id = 66666
o1.muestraId = () => console.log("El ID del objeto es " + this.id);
// delete o1.muestraId
MiClase.prototype.muestraId = () => console.log("Hola mundo")
o1.muestraId()
o2.muestraId.call(o1)
console.log(o1.muestraId == o2.muestraId)
console.log(++o1.cont, o2.cont++)
console.log(typeof MiClase, typeof Persona)
