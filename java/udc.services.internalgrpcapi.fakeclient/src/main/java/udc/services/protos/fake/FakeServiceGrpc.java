package udc.services.protos.fake;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
 *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
 *&lt;ul&gt;
 *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
 *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
 *&lt;/ul&gt;
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: fake.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FakeServiceGrpc {

  private FakeServiceGrpc() {}

  public static final String SERVICE_NAME = "udc.services.protos.fake.FakeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> getGetAllFakeBaseCentersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllFakeBaseCenters",
      requestType = com.google.protobuf.Empty.class,
      responseType = udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> getGetAllFakeBaseCentersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> getGetAllFakeBaseCentersMethod;
    if ((getGetAllFakeBaseCentersMethod = FakeServiceGrpc.getGetAllFakeBaseCentersMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetAllFakeBaseCentersMethod = FakeServiceGrpc.getGetAllFakeBaseCentersMethod) == null) {
          FakeServiceGrpc.getGetAllFakeBaseCentersMethod = getGetAllFakeBaseCentersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllFakeBaseCenters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetAllFakeBaseCenters"))
              .build();
        }
      }
    }
    return getGetAllFakeBaseCentersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> getGetAllFakeStudentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllFakeStudents",
      requestType = com.google.protobuf.Empty.class,
      responseType = udc.services.protos.fake.Fake.GetAllFakeStudentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> getGetAllFakeStudentsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> getGetAllFakeStudentsMethod;
    if ((getGetAllFakeStudentsMethod = FakeServiceGrpc.getGetAllFakeStudentsMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetAllFakeStudentsMethod = FakeServiceGrpc.getGetAllFakeStudentsMethod) == null) {
          FakeServiceGrpc.getGetAllFakeStudentsMethod = getGetAllFakeStudentsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, udc.services.protos.fake.Fake.GetAllFakeStudentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllFakeStudents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetAllFakeStudentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetAllFakeStudents"))
              .build();
        }
      }
    }
    return getGetAllFakeStudentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest,
      udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> getGetFakeStudentByIDDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFakeStudentByIDDocument",
      requestType = udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest.class,
      responseType = udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest,
      udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> getGetFakeStudentByIDDocumentMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest, udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> getGetFakeStudentByIDDocumentMethod;
    if ((getGetFakeStudentByIDDocumentMethod = FakeServiceGrpc.getGetFakeStudentByIDDocumentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetFakeStudentByIDDocumentMethod = FakeServiceGrpc.getGetFakeStudentByIDDocumentMethod) == null) {
          FakeServiceGrpc.getGetFakeStudentByIDDocumentMethod = getGetFakeStudentByIDDocumentMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest, udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFakeStudentByIDDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetFakeStudentByIDDocument"))
              .build();
        }
      }
    }
    return getGetFakeStudentByIDDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDRequest,
      udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> getGetFakeStudentByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFakeStudentByID",
      requestType = udc.services.protos.fake.Fake.GetFakeStudentByIDRequest.class,
      responseType = udc.services.protos.fake.Fake.GetFakeStudentByIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDRequest,
      udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> getGetFakeStudentByIDMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.GetFakeStudentByIDRequest, udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> getGetFakeStudentByIDMethod;
    if ((getGetFakeStudentByIDMethod = FakeServiceGrpc.getGetFakeStudentByIDMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetFakeStudentByIDMethod = FakeServiceGrpc.getGetFakeStudentByIDMethod) == null) {
          FakeServiceGrpc.getGetFakeStudentByIDMethod = getGetFakeStudentByIDMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.GetFakeStudentByIDRequest, udc.services.protos.fake.Fake.GetFakeStudentByIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFakeStudentByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetFakeStudentByIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.GetFakeStudentByIDResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetFakeStudentByID"))
              .build();
        }
      }
    }
    return getGetFakeStudentByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.CreateFakeStudentRequest,
      udc.services.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFakeStudent",
      requestType = udc.services.protos.fake.Fake.CreateFakeStudentRequest.class,
      responseType = udc.services.protos.fake.Fake.CreateFakeStudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.CreateFakeStudentRequest,
      udc.services.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.CreateFakeStudentRequest, udc.services.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod;
    if ((getCreateFakeStudentMethod = FakeServiceGrpc.getCreateFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getCreateFakeStudentMethod = FakeServiceGrpc.getCreateFakeStudentMethod) == null) {
          FakeServiceGrpc.getCreateFakeStudentMethod = getCreateFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.CreateFakeStudentRequest, udc.services.protos.fake.Fake.CreateFakeStudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.CreateFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.CreateFakeStudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("CreateFakeStudent"))
              .build();
        }
      }
    }
    return getCreateFakeStudentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest,
      com.google.protobuf.Empty> getDeleteFakeStudentByIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteFakeStudentByID",
      requestType = udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest,
      com.google.protobuf.Empty> getDeleteFakeStudentByIDMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest, com.google.protobuf.Empty> getDeleteFakeStudentByIDMethod;
    if ((getDeleteFakeStudentByIDMethod = FakeServiceGrpc.getDeleteFakeStudentByIDMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getDeleteFakeStudentByIDMethod = FakeServiceGrpc.getDeleteFakeStudentByIDMethod) == null) {
          FakeServiceGrpc.getDeleteFakeStudentByIDMethod = getDeleteFakeStudentByIDMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteFakeStudentByID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("DeleteFakeStudentByID"))
              .build();
        }
      }
    }
    return getDeleteFakeStudentByIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpdateFakeStudentRequest,
      com.google.protobuf.Empty> getUpdateFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateFakeStudent",
      requestType = udc.services.protos.fake.Fake.UpdateFakeStudentRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpdateFakeStudentRequest,
      com.google.protobuf.Empty> getUpdateFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpdateFakeStudentRequest, com.google.protobuf.Empty> getUpdateFakeStudentMethod;
    if ((getUpdateFakeStudentMethod = FakeServiceGrpc.getUpdateFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getUpdateFakeStudentMethod = FakeServiceGrpc.getUpdateFakeStudentMethod) == null) {
          FakeServiceGrpc.getUpdateFakeStudentMethod = getUpdateFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.UpdateFakeStudentRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.UpdateFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("UpdateFakeStudent"))
              .build();
        }
      }
    }
    return getUpdateFakeStudentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpsertFakeStudentRequest,
      udc.services.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpsertFakeStudent",
      requestType = udc.services.protos.fake.Fake.UpsertFakeStudentRequest.class,
      responseType = udc.services.protos.fake.Fake.UpsertFakeStudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpsertFakeStudentRequest,
      udc.services.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.protos.fake.Fake.UpsertFakeStudentRequest, udc.services.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod;
    if ((getUpsertFakeStudentMethod = FakeServiceGrpc.getUpsertFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getUpsertFakeStudentMethod = FakeServiceGrpc.getUpsertFakeStudentMethod) == null) {
          FakeServiceGrpc.getUpsertFakeStudentMethod = getUpsertFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.protos.fake.Fake.UpsertFakeStudentRequest, udc.services.protos.fake.Fake.UpsertFakeStudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpsertFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.UpsertFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.protos.fake.Fake.UpsertFakeStudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("UpsertFakeStudent"))
              .build();
        }
      }
    }
    return getUpsertFakeStudentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FakeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeServiceStub>() {
        @java.lang.Override
        public FakeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeServiceStub(channel, callOptions);
        }
      };
    return FakeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FakeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeServiceBlockingStub>() {
        @java.lang.Override
        public FakeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeServiceBlockingStub(channel, callOptions);
        }
      };
    return FakeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FakeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FakeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FakeServiceFutureStub>() {
        @java.lang.Override
        public FakeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FakeServiceFutureStub(channel, callOptions);
        }
      };
    return FakeServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
   *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
   *&lt;ul&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;/ul&gt;
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    default void getAllFakeBaseCenters(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllFakeBaseCentersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void getAllFakeStudents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllFakeStudentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its identifier document and ID type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void getFakeStudentByIDDocument(udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFakeStudentByIDDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void getFakeStudentByID(udc.services.protos.fake.Fake.GetFakeStudentByIDRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFakeStudentByIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void createFakeStudent(udc.services.protos.fake.Fake.CreateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.CreateFakeStudentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateFakeStudentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void deleteFakeStudentByID(udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteFakeStudentByIDMethod(), responseObserver);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void updateFakeStudent(udc.services.protos.fake.Fake.UpdateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateFakeStudentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void upsertFakeStudent(udc.services.protos.fake.Fake.UpsertFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.UpsertFakeStudentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpsertFakeStudentMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FakeService.
   * <pre>
   *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
   *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
   *&lt;ul&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;/ul&gt;
   * </pre>
   */
  public static abstract class FakeServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FakeServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FakeService.
   * <pre>
   *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
   *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
   *&lt;ul&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;/ul&gt;
   * </pre>
   */
  public static final class FakeServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FakeServiceStub> {
    private FakeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public void getAllFakeBaseCenters(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllFakeBaseCentersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void getAllFakeStudents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllFakeStudentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its identifier document and ID type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void getFakeStudentByIDDocument(udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFakeStudentByIDDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void getFakeStudentByID(udc.services.protos.fake.Fake.GetFakeStudentByIDRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFakeStudentByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void createFakeStudent(udc.services.protos.fake.Fake.CreateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.CreateFakeStudentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateFakeStudentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void deleteFakeStudentByID(udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteFakeStudentByIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void updateFakeStudent(udc.services.protos.fake.Fake.UpdateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateFakeStudentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void upsertFakeStudent(udc.services.protos.fake.Fake.UpsertFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.UpsertFakeStudentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpsertFakeStudentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FakeService.
   * <pre>
   *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
   *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
   *&lt;ul&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;/ul&gt;
   * </pre>
   */
  public static final class FakeServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FakeServiceBlockingStub> {
    private FakeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse getAllFakeBaseCenters(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllFakeBaseCentersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.GetAllFakeStudentsResponse getAllFakeStudents(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllFakeStudentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its identifier document and ID type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse getFakeStudentByIDDocument(udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFakeStudentByIDDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.GetFakeStudentByIDResponse getFakeStudentByID(udc.services.protos.fake.Fake.GetFakeStudentByIDRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFakeStudentByIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.CreateFakeStudentResponse createFakeStudent(udc.services.protos.fake.Fake.CreateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateFakeStudentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.protobuf.Empty deleteFakeStudentByID(udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteFakeStudentByIDMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.protobuf.Empty updateFakeStudent(udc.services.protos.fake.Fake.UpdateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateFakeStudentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.protos.fake.Fake.UpsertFakeStudentResponse upsertFakeStudent(udc.services.protos.fake.Fake.UpsertFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpsertFakeStudentMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FakeService.
   * <pre>
   *The Fake Service API allows you to create, get, update, upsert, and delete individual not-real students. It helps developers test API connection and flow meanwhile their real service API is not ready or not fully available.
   *Newest proto files, which define the contract of gRPC services and messages, are available to be downloaded from our repository:
   *&lt;ul&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/fake.proto" target="_blank"&gt;fake.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;li&gt;&lt;a href="https://github.com/carlosrodriguezd/udc.services/blob/main/src/Infraestructure/Udc.Services.Protos/core.proto" target="_blank"&gt;core.proto&lt;/a&gt;&lt;/li&gt;
   *&lt;/ul&gt;
   * </pre>
   */
  public static final class FakeServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FakeServiceFutureStub> {
    private FakeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FakeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FakeServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse> getAllFakeBaseCenters(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllFakeBaseCentersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.GetAllFakeStudentsResponse> getAllFakeStudents(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllFakeStudentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its identifier document and ID type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse> getFakeStudentByIDDocument(
        udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFakeStudentByIDDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.GetFakeStudentByIDResponse> getFakeStudentByID(
        udc.services.protos.fake.Fake.GetFakeStudentByIDRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFakeStudentByIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.CreateFakeStudentResponse> createFakeStudent(
        udc.services.protos.fake.Fake.CreateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateFakeStudentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteFakeStudentByID(
        udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteFakeStudentByIDMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateFakeStudent(
        udc.services.protos.fake.Fake.UpdateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateFakeStudentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.protos.fake.Fake.UpsertFakeStudentResponse> upsertFakeStudent(
        udc.services.protos.fake.Fake.UpsertFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpsertFakeStudentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_FAKE_BASE_CENTERS = 0;
  private static final int METHODID_GET_ALL_FAKE_STUDENTS = 1;
  private static final int METHODID_GET_FAKE_STUDENT_BY_IDDOCUMENT = 2;
  private static final int METHODID_GET_FAKE_STUDENT_BY_ID = 3;
  private static final int METHODID_CREATE_FAKE_STUDENT = 4;
  private static final int METHODID_DELETE_FAKE_STUDENT_BY_ID = 5;
  private static final int METHODID_UPDATE_FAKE_STUDENT = 6;
  private static final int METHODID_UPSERT_FAKE_STUDENT = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_FAKE_BASE_CENTERS:
          serviceImpl.getAllFakeBaseCenters((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_FAKE_STUDENTS:
          serviceImpl.getAllFakeStudents((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetAllFakeStudentsResponse>) responseObserver);
          break;
        case METHODID_GET_FAKE_STUDENT_BY_IDDOCUMENT:
          serviceImpl.getFakeStudentByIDDocument((udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse>) responseObserver);
          break;
        case METHODID_GET_FAKE_STUDENT_BY_ID:
          serviceImpl.getFakeStudentByID((udc.services.protos.fake.Fake.GetFakeStudentByIDRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.GetFakeStudentByIDResponse>) responseObserver);
          break;
        case METHODID_CREATE_FAKE_STUDENT:
          serviceImpl.createFakeStudent((udc.services.protos.fake.Fake.CreateFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.CreateFakeStudentResponse>) responseObserver);
          break;
        case METHODID_DELETE_FAKE_STUDENT_BY_ID:
          serviceImpl.deleteFakeStudentByID((udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_FAKE_STUDENT:
          serviceImpl.updateFakeStudent((udc.services.protos.fake.Fake.UpdateFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPSERT_FAKE_STUDENT:
          serviceImpl.upsertFakeStudent((udc.services.protos.fake.Fake.UpsertFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.protos.fake.Fake.UpsertFakeStudentResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetAllFakeBaseCentersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              udc.services.protos.fake.Fake.GetAllFakeBaseCentersResponse>(
                service, METHODID_GET_ALL_FAKE_BASE_CENTERS)))
        .addMethod(
          getGetAllFakeStudentsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              udc.services.protos.fake.Fake.GetAllFakeStudentsResponse>(
                service, METHODID_GET_ALL_FAKE_STUDENTS)))
        .addMethod(
          getGetFakeStudentByIDDocumentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentRequest,
              udc.services.protos.fake.Fake.GetFakeStudentByIDDocumentResponse>(
                service, METHODID_GET_FAKE_STUDENT_BY_IDDOCUMENT)))
        .addMethod(
          getGetFakeStudentByIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.GetFakeStudentByIDRequest,
              udc.services.protos.fake.Fake.GetFakeStudentByIDResponse>(
                service, METHODID_GET_FAKE_STUDENT_BY_ID)))
        .addMethod(
          getCreateFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.CreateFakeStudentRequest,
              udc.services.protos.fake.Fake.CreateFakeStudentResponse>(
                service, METHODID_CREATE_FAKE_STUDENT)))
        .addMethod(
          getDeleteFakeStudentByIDMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.DeleteFakeStudentByIDRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_FAKE_STUDENT_BY_ID)))
        .addMethod(
          getUpdateFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.UpdateFakeStudentRequest,
              com.google.protobuf.Empty>(
                service, METHODID_UPDATE_FAKE_STUDENT)))
        .addMethod(
          getUpsertFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.protos.fake.Fake.UpsertFakeStudentRequest,
              udc.services.protos.fake.Fake.UpsertFakeStudentResponse>(
                service, METHODID_UPSERT_FAKE_STUDENT)))
        .build();
  }

  private static abstract class FakeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FakeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return udc.services.protos.fake.Fake.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FakeService");
    }
  }

  private static final class FakeServiceFileDescriptorSupplier
      extends FakeServiceBaseDescriptorSupplier {
    FakeServiceFileDescriptorSupplier() {}
  }

  private static final class FakeServiceMethodDescriptorSupplier
      extends FakeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FakeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FakeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FakeServiceFileDescriptorSupplier())
              .addMethod(getGetAllFakeBaseCentersMethod())
              .addMethod(getGetAllFakeStudentsMethod())
              .addMethod(getGetFakeStudentByIDDocumentMethod())
              .addMethod(getGetFakeStudentByIDMethod())
              .addMethod(getCreateFakeStudentMethod())
              .addMethod(getDeleteFakeStudentByIDMethod())
              .addMethod(getUpdateFakeStudentMethod())
              .addMethod(getUpsertFakeStudentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
