JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Tema2.java

MAIN = Tema2

build: $(CLASSES:.java=.class)

clean:
	$(RM) *.class