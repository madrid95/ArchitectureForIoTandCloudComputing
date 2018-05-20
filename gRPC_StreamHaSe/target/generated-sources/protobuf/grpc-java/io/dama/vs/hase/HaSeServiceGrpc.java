package io.dama.vs.hase;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: hase.proto")
public final class HaSeServiceGrpc {

  private HaSeServiceGrpc() {}

  public static final String SERVICE_NAME = "hase.HaSeService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetSensorIdMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Empty,
      io.dama.vs.hase.Hase.Sensor> METHOD_GET_SENSOR_ID = getGetSensorIdMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Empty,
      io.dama.vs.hase.Hase.Sensor> getGetSensorIdMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Empty,
      io.dama.vs.hase.Hase.Sensor> getGetSensorIdMethod() {
    return getGetSensorIdMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Empty,
      io.dama.vs.hase.Hase.Sensor> getGetSensorIdMethodHelper() {
    io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Empty, io.dama.vs.hase.Hase.Sensor> getGetSensorIdMethod;
    if ((getGetSensorIdMethod = HaSeServiceGrpc.getGetSensorIdMethod) == null) {
      synchronized (HaSeServiceGrpc.class) {
        if ((getGetSensorIdMethod = HaSeServiceGrpc.getGetSensorIdMethod) == null) {
          HaSeServiceGrpc.getGetSensorIdMethod = getGetSensorIdMethod = 
              io.grpc.MethodDescriptor.<io.dama.vs.hase.Hase.Empty, io.dama.vs.hase.Hase.Sensor>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "hase.HaSeService", "getSensorId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Sensor.getDefaultInstance()))
                  .setSchemaDescriptor(new HaSeServiceMethodDescriptorSupplier("getSensorId"))
                  .build();
          }
        }
     }
     return getGetSensorIdMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSwitchOffMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Boolean> METHOD_SWITCH_OFF = getSwitchOffMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Boolean> getSwitchOffMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Boolean> getSwitchOffMethod() {
    return getSwitchOffMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Boolean> getSwitchOffMethodHelper() {
    io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Boolean> getSwitchOffMethod;
    if ((getSwitchOffMethod = HaSeServiceGrpc.getSwitchOffMethod) == null) {
      synchronized (HaSeServiceGrpc.class) {
        if ((getSwitchOffMethod = HaSeServiceGrpc.getSwitchOffMethod) == null) {
          HaSeServiceGrpc.getSwitchOffMethod = getSwitchOffMethod = 
              io.grpc.MethodDescriptor.<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Boolean>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "hase.HaSeService", "switchOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Sensor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Boolean.getDefaultInstance()))
                  .setSchemaDescriptor(new HaSeServiceMethodDescriptorSupplier("switchOff"))
                  .build();
          }
        }
     }
     return getSwitchOffMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetObservationMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> METHOD_GET_OBSERVATION = getGetObservationMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getGetObservationMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getGetObservationMethod() {
    return getGetObservationMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getGetObservationMethodHelper() {
    io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Telegram> getGetObservationMethod;
    if ((getGetObservationMethod = HaSeServiceGrpc.getGetObservationMethod) == null) {
      synchronized (HaSeServiceGrpc.class) {
        if ((getGetObservationMethod = HaSeServiceGrpc.getGetObservationMethod) == null) {
          HaSeServiceGrpc.getGetObservationMethod = getGetObservationMethod = 
              io.grpc.MethodDescriptor.<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Telegram>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "hase.HaSeService", "getObservation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Sensor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Telegram.getDefaultInstance()))
                  .setSchemaDescriptor(new HaSeServiceMethodDescriptorSupplier("getObservation"))
                  .build();
          }
        }
     }
     return getGetObservationMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStreamObservationsMethod()} instead. 
  public static final io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> METHOD_STREAM_OBSERVATIONS = getStreamObservationsMethodHelper();

  private static volatile io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getStreamObservationsMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getStreamObservationsMethod() {
    return getStreamObservationsMethodHelper();
  }

  private static io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor,
      io.dama.vs.hase.Hase.Telegram> getStreamObservationsMethodHelper() {
    io.grpc.MethodDescriptor<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Telegram> getStreamObservationsMethod;
    if ((getStreamObservationsMethod = HaSeServiceGrpc.getStreamObservationsMethod) == null) {
      synchronized (HaSeServiceGrpc.class) {
        if ((getStreamObservationsMethod = HaSeServiceGrpc.getStreamObservationsMethod) == null) {
          HaSeServiceGrpc.getStreamObservationsMethod = getStreamObservationsMethod = 
              io.grpc.MethodDescriptor.<io.dama.vs.hase.Hase.Sensor, io.dama.vs.hase.Hase.Telegram>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "hase.HaSeService", "streamObservations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Sensor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.dama.vs.hase.Hase.Telegram.getDefaultInstance()))
                  .setSchemaDescriptor(new HaSeServiceMethodDescriptorSupplier("streamObservations"))
                  .build();
          }
        }
     }
     return getStreamObservationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HaSeServiceStub newStub(io.grpc.Channel channel) {
    return new HaSeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HaSeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HaSeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HaSeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HaSeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class HaSeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSensorId(io.dama.vs.hase.Hase.Empty request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Sensor> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSensorIdMethodHelper(), responseObserver);
    }

    /**
     */
    public void switchOff(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Boolean> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchOffMethodHelper(), responseObserver);
    }

    /**
     */
    public void getObservation(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram> responseObserver) {
      asyncUnimplementedUnaryCall(getGetObservationMethodHelper(), responseObserver);
    }

    /**
     */
    public void streamObservations(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamObservationsMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSensorIdMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.dama.vs.hase.Hase.Empty,
                io.dama.vs.hase.Hase.Sensor>(
                  this, METHODID_GET_SENSOR_ID)))
          .addMethod(
            getSwitchOffMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.dama.vs.hase.Hase.Sensor,
                io.dama.vs.hase.Hase.Boolean>(
                  this, METHODID_SWITCH_OFF)))
          .addMethod(
            getGetObservationMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                io.dama.vs.hase.Hase.Sensor,
                io.dama.vs.hase.Hase.Telegram>(
                  this, METHODID_GET_OBSERVATION)))
          .addMethod(
            getStreamObservationsMethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.dama.vs.hase.Hase.Sensor,
                io.dama.vs.hase.Hase.Telegram>(
                  this, METHODID_STREAM_OBSERVATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class HaSeServiceStub extends io.grpc.stub.AbstractStub<HaSeServiceStub> {
    private HaSeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HaSeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HaSeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HaSeServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSensorId(io.dama.vs.hase.Hase.Empty request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Sensor> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSensorIdMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void switchOff(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Boolean> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchOffMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getObservation(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetObservationMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamObservations(io.dama.vs.hase.Hase.Sensor request,
        io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamObservationsMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HaSeServiceBlockingStub extends io.grpc.stub.AbstractStub<HaSeServiceBlockingStub> {
    private HaSeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HaSeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HaSeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HaSeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.dama.vs.hase.Hase.Sensor getSensorId(io.dama.vs.hase.Hase.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetSensorIdMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.dama.vs.hase.Hase.Boolean switchOff(io.dama.vs.hase.Hase.Sensor request) {
      return blockingUnaryCall(
          getChannel(), getSwitchOffMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public io.dama.vs.hase.Hase.Telegram getObservation(io.dama.vs.hase.Hase.Sensor request) {
      return blockingUnaryCall(
          getChannel(), getGetObservationMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.dama.vs.hase.Hase.Telegram> streamObservations(
        io.dama.vs.hase.Hase.Sensor request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamObservationsMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HaSeServiceFutureStub extends io.grpc.stub.AbstractStub<HaSeServiceFutureStub> {
    private HaSeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HaSeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HaSeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HaSeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.dama.vs.hase.Hase.Sensor> getSensorId(
        io.dama.vs.hase.Hase.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSensorIdMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.dama.vs.hase.Hase.Boolean> switchOff(
        io.dama.vs.hase.Hase.Sensor request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchOffMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.dama.vs.hase.Hase.Telegram> getObservation(
        io.dama.vs.hase.Hase.Sensor request) {
      return futureUnaryCall(
          getChannel().newCall(getGetObservationMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SENSOR_ID = 0;
  private static final int METHODID_SWITCH_OFF = 1;
  private static final int METHODID_GET_OBSERVATION = 2;
  private static final int METHODID_STREAM_OBSERVATIONS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HaSeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HaSeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SENSOR_ID:
          serviceImpl.getSensorId((io.dama.vs.hase.Hase.Empty) request,
              (io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Sensor>) responseObserver);
          break;
        case METHODID_SWITCH_OFF:
          serviceImpl.switchOff((io.dama.vs.hase.Hase.Sensor) request,
              (io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Boolean>) responseObserver);
          break;
        case METHODID_GET_OBSERVATION:
          serviceImpl.getObservation((io.dama.vs.hase.Hase.Sensor) request,
              (io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram>) responseObserver);
          break;
        case METHODID_STREAM_OBSERVATIONS:
          serviceImpl.streamObservations((io.dama.vs.hase.Hase.Sensor) request,
              (io.grpc.stub.StreamObserver<io.dama.vs.hase.Hase.Telegram>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HaSeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HaSeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.dama.vs.hase.Hase.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HaSeService");
    }
  }

  private static final class HaSeServiceFileDescriptorSupplier
      extends HaSeServiceBaseDescriptorSupplier {
    HaSeServiceFileDescriptorSupplier() {}
  }

  private static final class HaSeServiceMethodDescriptorSupplier
      extends HaSeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HaSeServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HaSeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HaSeServiceFileDescriptorSupplier())
              .addMethod(getGetSensorIdMethodHelper())
              .addMethod(getSwitchOffMethodHelper())
              .addMethod(getGetObservationMethodHelper())
              .addMethod(getStreamObservationsMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
