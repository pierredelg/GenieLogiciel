png(filename="comparaison.png")
eratosthene <- read.table("Eratosthene.csv", header=TRUE)
eratostheneRapide <- read.table("EratostheneRapide.csv", header=TRUE)
plot(eratosthene$duree ~ eratosthene$N, type="o", col="blue",
     main="Comparaison du temps de calcul pour les deux classes",
     xlab="valeur de N", ylab="t (s)")
lines(eratostheneRapide$duree ~ eratostheneRapide$N, type="o",
      col="red")
legend(10,c("Eratosthene", "EratostheneRapide"), fill=c("blue","red"))
