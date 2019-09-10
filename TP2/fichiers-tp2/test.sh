echo -e "N\tduree"
for i in {10,20,30,40,50,75,100,125,150,175,200,250,300}000
do 
    echo -n -e "$i\t"
    (time -p java -classpath classes -Xss100M $1 $i) 2>&1 |grep user|cut -d' ' -f2 
done
