
## Escuela Colombiana de Ingeniería
### Arquitecturas de Software
### Introducción al paralelismo - hilos

Brayan Castiblanco - Francisco Márquez


**Parte I Hilos Java**

1. De acuerdo con lo revisado en las lecturas, complete las clases CountThread, para que las mismas definan el ciclo de vida de un hilo que imprima por pantalla los números entre A y B.

![image](https://user-images.githubusercontent.com/98216991/216255348-acbcf912-e9e5-465a-b3c9-f9aa3695b25a.png)


2. Complete el método __main__ de la clase CountMainThreads para que:
	1. Cree 3 hilos de tipo CountThread, asignándole al primero el intervalo [0..99], al segundo [99..199], y al tercero [200..299].
	
	- Se crean los 3 hilos.
	
	<img width="431" alt="image" src="https://user-images.githubusercontent.com/98216991/216256484-3ad36eb8-d077-4309-96b5-9021b60263db.png">
	
	3. Inicie los tres hilos con 'start()'.
	4. Ejecute y revise la salida por pantalla. 
	
	<img width="300" alt="image" src="https://user-images.githubusercontent.com/98216991/216257309-eccdb206-c09b-4466-9523-9ba55f9fd912.png">

	6. Cambie el incio con 'start()' por 'run()'. Cómo cambia la salida?, por qué?.
	
	La salida cambia completamente, pues al iniciarse un Thread con start() sí se genera un hilo como tal, mientras que al hacerlo con run() únicamente se llama a 		ese método, no se inicia un hilo como tal.
	
	
	<img width="300" alt="image" src="https://user-images.githubusercontent.com/98216991/216257657-a878dd49-52f3-4960-93e0-e34a582688c6.png">


**Parte II Hilos Java**

La fórmula [BBP](https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula) (Bailey–Borwein–Plouffe formula) es un algoritmo que permite calcular el enésimo dígito de PI en base 16, con la particularidad de no necesitar calcular nos n-1 dígitos anteriores. Esta característica permite convertir el problema de calcular un número masivo de dígitos de PI (en base 16) a uno [vergonzosamente paralelo](https://en.wikipedia.org/wiki/Embarrassingly_parallel). En este repositorio encontrará la implementación, junto con un conjunto de pruebas. 

Para este ejercicio se quiere calcular, en el menor tiempo posible, y en una sola máquina (aprovechando las características multi-core de la mismas) al menos el primer millón de dígitos de PI (en base 16). Para esto

1. Cree una clase de tipo Thread que represente el ciclo de vida de un hilo que calcule una parte de los dígitos requeridos.
2. Haga que la función PiDigits.getDigits() reciba como parámetro adicional un valor N, correspondiente al número de hilos entre los que se va a paralelizar la solución. Haga que dicha función espere hasta que los N hilos terminen de resolver el problema para combinar las respuestas y entonces retornar el resultado. Para esto, revise el método [join](https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html) del API de concurrencia de Java.
3. Ajuste las pruebas de JUnit, considerando los casos de usar 1, 2 o 3 hilos (este último para considerar un número impar de hilos!)

<img width="480" alt="image" src="https://user-images.githubusercontent.com/98216991/216259706-b387a0d9-9cf8-4961-ab0e-d109631216fc.png">


<img width="461" alt="image" src="https://user-images.githubusercontent.com/98216991/216259521-4473e524-c376-4297-9e2a-c63d787a20e5.png">


<img width="462" alt="image" src="https://user-images.githubusercontent.com/98216991/216259833-2e73fafd-b3ee-4545-b67e-0e2da620f9af.png">



**Parte III Evaluación de Desempeño**

A partir de lo anterior, implemente la siguiente secuencia de experimentos para calcular el millon de dígitos (hex) de PI, tomando los tiempos de ejecución de los mismos (asegúrese de hacerlos en la misma máquina):

1. Un solo hilo.
<img width="464" alt="image" src="https://user-images.githubusercontent.com/98216991/216274237-dbefcbab-7947-4b89-a6b0-c333d83a8391.png">


2. Tantos hilos como núcleos de procesamiento (haga que el programa determine esto haciendo uso del [API Runtime]

<img width="463" alt="image" src="https://user-images.githubusercontent.com/98216991/216275596-9216d3fc-acd4-4cf4-9e5e-af0fa8281bd2.png">

<img width="295" alt="image" src="https://user-images.githubusercontent.com/98216991/216275936-afbc50d0-9a55-4a62-9049-e79f0ededeb0.png">

Por lo tanto con 8 hilos:

<img width="461" alt="image" src="https://user-images.githubusercontent.com/98216991/216276375-6ccbfcf5-2225-475d-8f76-1428732a01ce.png">



3. Tantos hilos como el doble de núcleos de procesamiento.

16 hilos:

<img width="461" alt="image" src="https://user-images.githubusercontent.com/98216991/216276709-aa05bb2d-c2d8-4356-9243-b016afd4f12e.png">

5. 200 hilos.



7. 500 hilos.

Al iniciar el programa ejecute el monitor jVisualVM, y a medida que corran las pruebas, revise y anote el consumo de CPU y de memoria en cada caso. ![](img/jvisualvm.png)

Con lo anterior, y con los tiempos de ejecución dados, haga una gráfica de tiempo de solución vs. número de hilos. Analice y plantee hipótesis con su compañero para las siguientes preguntas (puede tener en cuenta lo reportado por jVisualVM):



1. Según la [ley de Amdahls](https://www.pugetsystems.com/labs/articles/Estimating-CPU-Performance-using-Amdahls-Law-619/#WhatisAmdahlsLaw?):

	![](img/ahmdahls.png), donde _S(n)_ es el mejoramiento teórico del desempeño, _P_ la fracción paralelizable del algoritmo, y _n_ el número de hilos, a mayor _n_, mayor debería ser dicha mejora. Por qué el mejor desempeño no se logra con los 500 hilos?, cómo se compara este desempeño cuando se usan 200?. 

2. Cómo se comporta la solución usando tantos hilos de procesamiento como núcleos comparado con el resultado de usar el doble de éste?.

3. De acuerdo con lo anterior, si para este problema en lugar de 500 hilos en una sola CPU se pudiera usar 1 hilo en cada una de 500 máquinas hipotéticas, la ley de Amdahls se aplicaría mejor?. Si en lugar de esto se usaran c hilos en 500/c máquinas distribuidas (siendo c es el número de núcleos de dichas máquinas), se mejoraría?. Explique su respuesta.



#### Criterios de evaluación.

1. Funcionalidad:
	- El problema fue paralelizado (el tiempo de ejecución se reduce y el uso de los núcleos aumenta), y permite parametrizar el número de hilos usados simultáneamente.

2. Diseño:
	- La signatura del método original sólo fue modificada con el parámetro original, y en el mismo debe quedar encapsulado la paralelización e inicio de la solución, y la sincronización de la finalización de la misma.
	- Las nuevas pruebas con sólo UN hilo deben ser exactamente iguales a las originales, variando sólo el parámetro adicional. Se incluyeron pruebas con hilos adicionales, y las mismas pasan.
	- Se plantea un método eficiente para combinar los resultados en el orden correcto (iterar sobre cada resultado NO sera eficiente).

3. Análisis.
	- Se deja evidencia de la realización de los experimentos.
	- Los análisis realizados son consistentes.
