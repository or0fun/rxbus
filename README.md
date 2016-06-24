# rxbus
RxBus is is a publish/subscribe event bus optimized for Android using RxJava.

    //Post Event
    RxBus.getDefault().post(new TestEvent("test RxBus"));
   
    //support StickyEvent
    RxBus.getDefault().postSticky(new TestEvent("test RxBus sticky"));
    
    
    //subscribe event
    subscription = RxBus.getDefault().subscribe(TestEvent.class, testEvent -> {
            Toast.makeText(MainActivity.this, testEvent.test, Toast.LENGTH_SHORT).show();
        }, throwable -> {

        });
        
    
    //support StickyEvent
    subscription = RxBus.getDefault().subscribeSticky(TestEvent.class,
                testEvent -> textTextView.setText(testEvent.test),
                null);
                
    
    取消订阅            
     @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
