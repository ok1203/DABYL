/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package lineageos.weather;
/** @hide */
public interface IWeatherServiceProviderChangeListener extends android.os.IInterface
{
  /** Default implementation for IWeatherServiceProviderChangeListener. */
  public static class Default implements lineageos.weather.IWeatherServiceProviderChangeListener
  {
    @Override public void onWeatherServiceProviderChanged(java.lang.String providerLabel) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements lineageos.weather.IWeatherServiceProviderChangeListener
  {
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an lineageos.weather.IWeatherServiceProviderChangeListener interface,
     * generating a proxy if needed.
     */
    public static lineageos.weather.IWeatherServiceProviderChangeListener asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof lineageos.weather.IWeatherServiceProviderChangeListener))) {
        return ((lineageos.weather.IWeatherServiceProviderChangeListener)iin);
      }
      return new lineageos.weather.IWeatherServiceProviderChangeListener.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
      }
      switch (code)
      {
        case TRANSACTION_onWeatherServiceProviderChanged:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          this.onWeatherServiceProviderChanged(_arg0);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements lineageos.weather.IWeatherServiceProviderChangeListener
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void onWeatherServiceProviderChanged(java.lang.String providerLabel) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(providerLabel);
          boolean _status = mRemote.transact(Stub.TRANSACTION_onWeatherServiceProviderChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
          if (!_status) {
            if (getDefaultImpl() != null) {
              getDefaultImpl().onWeatherServiceProviderChanged(providerLabel);
              return;
            }
          }
        }
        finally {
          _data.recycle();
        }
      }
      public static lineageos.weather.IWeatherServiceProviderChangeListener sDefaultImpl;
    }
    static final int TRANSACTION_onWeatherServiceProviderChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(lineageos.weather.IWeatherServiceProviderChangeListener impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static lineageos.weather.IWeatherServiceProviderChangeListener getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public static final java.lang.String DESCRIPTOR = "lineageos.weather.IWeatherServiceProviderChangeListener";
  public void onWeatherServiceProviderChanged(java.lang.String providerLabel) throws android.os.RemoteException;
}
