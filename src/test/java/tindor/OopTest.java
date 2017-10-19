package tindor;

import org.junit.Test;

/**
 * @author CodePorter
 * @date 2017-10-13
 */
public class OopTest {
    class Supper {
        public Supper() {
            System.out.println("supper constructor::Supper()");
        }

        public Supper(String id) {
            this();
            System.out.println("supper constructor::Supper(String id)");
        }

        public void method1() {
            System.out.println("supper method::method1()");
        }

        public void method2() {
            this.method1();
            System.out.println("supper method::method2()");
        }
    }

    class Sub extends Supper {
        public Sub() {
            System.out.println("sub constructor::Sub()");
        }

        public Sub(String id) {
            super(id);
            System.out.println("sub constructor::Sub(String id)");
        }

        @Override
        public void method1() {
            System.out.println("sub method::method1()");
        }

        @Override
        public void method2() {
            super.method2();
            System.out.println("sub method:method2()");
        }
    }

    @Test
    public void testOop() throws Exception {
        Sub sub = new Sub("subclass");
        sub.method2();
    }
}
