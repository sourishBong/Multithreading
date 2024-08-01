package rate.limiting.using.token.bucket.using.simple.factory;

public class TokenBucketFilterFactory {

    private static class MultithreadedTokenBucketFilter implements TokenBucketFilter{

        private long possibleToken=0;
        private final int MAX_TOKEN;
        private final int ONE_SECOND=1000;

        private MultithreadedTokenBucketFilter(int max_token) {
            MAX_TOKEN = max_token;
        }

        private void daemonThread(){
            while(true){
                synchronized (this){
                    if(possibleToken<MAX_TOKEN)
                        possibleToken++;
                    this.notify();
                }
                try{
                    Thread.sleep(ONE_SECOND);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }

        void initialize(){
            //never start a thread in a constructor
            Thread dt=new Thread(()->{
                daemonThread();
            });

            dt.setDaemon(true);
            dt.start();
        }

        @Override
        public void getToken() throws InterruptedException {
            synchronized (this){
                while(possibleToken==0) {
                    this.wait();
                }
                possibleToken--;
            }
            System.out.println("Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / 1000);
        }
    }

    // Force users to interact with the factory
    // only through the static methods
    private TokenBucketFilterFactory() {
    }

    static public TokenBucketFilter makeTokenBucketFilter(int capacity) {
        MultithreadedTokenBucketFilter tbf = new MultithreadedTokenBucketFilter(capacity);
        tbf.initialize();
        return tbf;
    }
}
