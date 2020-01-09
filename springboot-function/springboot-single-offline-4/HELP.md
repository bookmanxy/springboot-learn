第四种方法，通过调用一个SpringApplication.exit(）方法也可以退出程序，同时将生成一个退出码，这个退出码可以传递给所有的context。

这个就是一个JVM的钩子，通过调用这个方法的话会把所有PreDestroy的方法执行并停止，并且传递给具体的退出码给所有Context。通过调用System.exit(exitCode)可以将这个错误码也传给JVM。程序执行完后最后会输出：Process finished with exit code 0，给JVM一个SIGNAL。

          /* method 4: exit this application using static method */
        ConfigurableApplicationContext ctx = SpringApplication.run(ShutdowndemoApplication.class, args);
        exitApplication(ctx);


    public static void exitApplication(ConfigurableApplicationContext context) {
        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);

        System.exit(exitCode);
    }
