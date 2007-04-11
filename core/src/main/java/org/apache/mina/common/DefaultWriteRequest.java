package org.apache.mina.common;

import java.net.SocketAddress;

/**
 * The default implementation of {@link WriteRequest}.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev$, $Date$
 */
public class DefaultWriteRequest implements WriteRequest
{
    private static final WriteFuture UNUSED_FUTURE = new WriteFuture()
    {
        public boolean isWritten()
        {
            return false;
        }

        public void setWritten( boolean written )
        {
        }
        
        public IoSession getSession()
        {
            return null;
        }

        public void join()
        {
        }

        public boolean join( long timeoutInMillis )
        {
            return true;
        }

        public boolean isReady()
        {
            return true;
        }

        public void addListener( IoFutureListener listener )
        {
            throw new IllegalStateException( "You can't add a listener to a dummy future." );
        }

        public void removeListener( IoFutureListener listener )
        {
            throw new IllegalStateException( "You can't add a listener to a dummy future." );
        }
    };
    
    private final Object message;
    private final WriteFuture future;
    private final SocketAddress destination;
    
    /**
     * Creates a new instance without {@link WriteFuture}.  You'll get
     * an instance of {@link WriteFuture} even if you called this constructor
     * because {@link #getFuture()} will return a bogus future.
     */
    public DefaultWriteRequest( Object message )
    {
        this( message, null, null );
    }

    /**
     * Creates a new instance with {@link WriteFuture}.
     */
    public DefaultWriteRequest( Object message, WriteFuture future )
    {
        this( message, future, null );
    }
    
    /**
     * Creates a new instance.
     * 
     * @param message a message to write
     * @param future a future that needs to be notified when an operation is finished
     * @param destination the destination of the message.  This property will be
     *                    ignored unless the transport supports it.
     */
    public DefaultWriteRequest( Object message, WriteFuture future, SocketAddress destination )
    {
        if( message == null )
        {
            throw new NullPointerException( "message" );
        }
        
        if( future == null )
        {
            future = UNUSED_FUTURE;
        }
        
        this.message = message;
        this.future = future;
        this.destination = destination;
    }

    /**
     * Returns {@link WriteFuture} that is associated with this write request.
     */
    public WriteFuture getFuture()
    {
        return future;
    }

    /**
     * Returns a message object to be written.
     */
    public Object getMessage()
    {
        return message;
    }
    
    /**
     * Returne the destination of this write request.
     * 
     * @return <tt>null</tt> for the default destination
     */
    public SocketAddress getDestination() 
    {
        return destination;
    }
    
    @Override
    public String toString()
    {
        return message.toString();
    }
}