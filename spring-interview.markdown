1. spring IOC(DI), AOP
2. 容器是applicationContext 还是BeanFactory
spring 自带多个容器实现，可以归为两种类型的实现。beanFactory是最简单的容器，提供基本的DI支持。applicationContext基于beanFactory构建，
并提供应用框架级别的服务，例如从属性文件解析文本信息以及发布应用事件给感兴趣的事件监听者。 

applicationContext类型：
- ClassPathXmlApplicationContext
- FileSystemXmlApplicationContext
- ...

3. BeanFactory 与 factoryBean的区别
beanFactory是IOC容器的基础，applicationContext就实现了beanFactory, 提供了获取bean和管理bean的方式。

factoryBean是实现工厂实现的接口，是一个工厂，用来创建指定的对象，常用的场景是创建一个复杂的对象，这个对象需要依赖其他对象或者配置。
生成的bean也受beanFactory管理。

4. spring是如何解决bean的相互依赖的
5. bean的生命周期

6. bean的作用域
默认情况下，spring中的bean都是以单例的形式创建的。但是这种bean通常是有状态的，对象会被污染。
spring定义了多种域：
单例(singleton): 在整个应用中，只创建bean的一个实例
原型(prototype): 每次注入或者通过spring context获取的时候，都会创建一个新的实例。
回话(session): 在web中，为每一个回话创建一个实例
请求(request): 在web中，为每一个请求创建一个实例

7. 何时织入
编译期：切面在目标编译时被织入，这种方式需要增强的编译器。aspectJ的织入编译器就是以这种方式织入的。
类加载期：切面在目标类加载到JVM时被织入，这种方式需要特殊的类加载器（ClassLoader），它可以在目标类被引入应用之前增强该目标类的字节码。
AspectJ 5 的加载时织入（load-time weaving，LTW）就支持以这种方式织入切面。
运行期：切面在应用运行的某个时刻被织入。一般情况下，在织入切面时，AOP 容器会为目标对象动态地创建一个代理对象。Spring AOP 就是以这种方式织入切面的。

8. spring bean是一直存在于容器中吗？
 我对这个表示怀疑，如果一直存在那么会出现内存溢出，JVM gc 也就没什么用了。单例会一直存在，如果每次创建一个新的对象，那么应该还是会被回收的。