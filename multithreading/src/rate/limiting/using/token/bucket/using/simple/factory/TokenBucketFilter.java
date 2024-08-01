package rate.limiting.using.token.bucket.using.simple.factory;

public interface TokenBucketFilter {

    public void getToken()throws InterruptedException;
}
