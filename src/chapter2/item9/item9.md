# try-finally
~~~java
static String firstLineOfFile(String path) throws IOException{
	BufferedReader br = new BufferedReader(new FileReader(path));
    try{
    	return br.readLine();
    }finally{
    	br.close();
    }
}
~~~

>- BufferedReader를 사용하고 난후 마지막에 close 로 닫아주었다.

~~~java
static void copy(String src, String dst) throws IOException{
	InputStream in = new FileInputStream(src);
    try{
    	OutputStream out = new FileOutputStream(dst);
        try{
        	byte[]buf = new byte[BUFFER_SIZE];
            int n;
            while((n = in.read(buf)) >= 0)
            	out.write(buf,0,n);
        }finally{
        	out.close();
        }
    }finally{
    	in.close();
    }
}
~~~

>- 자원이 두개인 경우이다.
- FileInputStream 과 FileOutputStream 두가지 커넥션을 사용중이다.

# 문제점

>- firstLineOfFile 과 close메서드 모두에서 예외가 발생하게 될경우 두번째 예외가 첫번째 에외를 집어삼켜 디버깅에 어려움이 발생한다.

# 해결법

## 1.
~~~java
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
~~~

## 2.
~~~java
static void copy(String src,String dst) throws IOException{
	try(InputStream in = new FileInputStream(src);
    	OutputStream out = new FileOutputStream(dst)){
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while((n = in.read(buf)) >= 0)
        	out.write(buf , 0,n);
        }
}
~~~

>- 두가지 모두 try-with-resources 로 변경하였다.
- 짧고 읽기 수월하며 문제를 진단하기에도 좋다.
- readLine 과 close 양쪽에서 예외가 발생하면 close 에서 발생한 예외는 숨겨지고 readLine 에서 발생한 예외가 기록된다.
- try-with-reasources 에서도 catch 를 사용할수 있는데 catch 덕분에 try 문을 더 중첩하지 않고도 다수의 예외를 처리할수 있다.

## 3.
~~~java
static String firstLineOfFile(String path, String defaultValue){
	try(BufferdReader br = new BufferedReader(new FileReader(path))){
    	return br.readLine();
    }catch(IOException e){
    	return defaultValue;
    }
}
~~~

# 결론
>- 꼭 회수해야 하는 자원을 다룰때 try-with-resources를 사용하자.
- 코드를 더 짧고 분명하게 할수 있으며 정확하고 쉽게 자원을 회수할수 있다.