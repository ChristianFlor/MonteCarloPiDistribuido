Este proyecto está soportado en cualquier sistema operativo.

Aunque el proyecto está implementado en varios componentes SCA independientes y desplegables en cualquier máquina, en la configuración actual todos ellos están configurados para ser desplegados en la misma máquina (nodo de procesamiento).

=============================
PATRONES DE DISEÑO
En cuanto al patrón Proxy se implementaron para realizar la sincronización de los hilos que intentan pedir y enviar información desde los diferentes nodos de tipo procesadorPuntos. 
==========================
Para montar el proyecto, se debe tener configurado FraSCAti y se deben seguir los siguientes pasos:

1)Estando en src, compilar los proyectos: 	

frascati compile MonteCarlo/src MonteCarlo
frascati compile Proxy/src Proxy
frascati compile ProcesadorPuntos/src ProcesadorPuntos

2) Correr los proyectos en el siguiente orden y con su respectivo archivo composite:
frascati run MonteCarlo -libpath MonteCarlo.jar -s r -m run
frascati run Proxy -libpath Proxy.jar -s r -m run
frascati run ProcesadorPuntos -libpath ProcesadorPuntos.jar -s r -m run
