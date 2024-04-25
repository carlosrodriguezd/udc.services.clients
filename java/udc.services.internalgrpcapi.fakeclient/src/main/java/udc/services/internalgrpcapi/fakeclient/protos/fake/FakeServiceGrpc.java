package udc.services.internalgrpcapi.fakeclient.protos.fake;

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
  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> getGetFakeStudentByIdDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFakeStudentByIdDocument",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> getGetFakeStudentByIdDocumentMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> getGetFakeStudentByIdDocumentMethod;
    if ((getGetFakeStudentByIdDocumentMethod = FakeServiceGrpc.getGetFakeStudentByIdDocumentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetFakeStudentByIdDocumentMethod = FakeServiceGrpc.getGetFakeStudentByIdDocumentMethod) == null) {
          FakeServiceGrpc.getGetFakeStudentByIdDocumentMethod = getGetFakeStudentByIdDocumentMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFakeStudentByIdDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetFakeStudentByIdDocument"))
              .build();
        }
      }
    }
    return getGetFakeStudentByIdDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> getGetFakeStudentByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFakeStudentById",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> getGetFakeStudentByIdMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> getGetFakeStudentByIdMethod;
    if ((getGetFakeStudentByIdMethod = FakeServiceGrpc.getGetFakeStudentByIdMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getGetFakeStudentByIdMethod = FakeServiceGrpc.getGetFakeStudentByIdMethod) == null) {
          FakeServiceGrpc.getGetFakeStudentByIdMethod = getGetFakeStudentByIdMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFakeStudentById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("GetFakeStudentById"))
              .build();
        }
      }
    }
    return getGetFakeStudentByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> getListFakeBaseCentersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListFakeBaseCenters",
      requestType = com.google.protobuf.Empty.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> getListFakeBaseCentersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> getListFakeBaseCentersMethod;
    if ((getListFakeBaseCentersMethod = FakeServiceGrpc.getListFakeBaseCentersMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getListFakeBaseCentersMethod = FakeServiceGrpc.getListFakeBaseCentersMethod) == null) {
          FakeServiceGrpc.getListFakeBaseCentersMethod = getListFakeBaseCentersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListFakeBaseCenters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("ListFakeBaseCenters"))
              .build();
        }
      }
    }
    return getListFakeBaseCentersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> getListFakeStudentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListFakeStudents",
      requestType = com.google.protobuf.Empty.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> getListFakeStudentsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> getListFakeStudentsMethod;
    if ((getListFakeStudentsMethod = FakeServiceGrpc.getListFakeStudentsMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getListFakeStudentsMethod = FakeServiceGrpc.getListFakeStudentsMethod) == null) {
          FakeServiceGrpc.getListFakeStudentsMethod = getListFakeStudentsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListFakeStudents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("ListFakeStudents"))
              .build();
        }
      }
    }
    return getListFakeStudentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFakeStudent",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> getCreateFakeStudentMethod;
    if ((getCreateFakeStudentMethod = FakeServiceGrpc.getCreateFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getCreateFakeStudentMethod = FakeServiceGrpc.getCreateFakeStudentMethod) == null) {
          FakeServiceGrpc.getCreateFakeStudentMethod = getCreateFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("CreateFakeStudent"))
              .build();
        }
      }
    }
    return getCreateFakeStudentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest,
      com.google.protobuf.Empty> getDeleteFakeStudentByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteFakeStudentById",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest,
      com.google.protobuf.Empty> getDeleteFakeStudentByIdMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest, com.google.protobuf.Empty> getDeleteFakeStudentByIdMethod;
    if ((getDeleteFakeStudentByIdMethod = FakeServiceGrpc.getDeleteFakeStudentByIdMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getDeleteFakeStudentByIdMethod = FakeServiceGrpc.getDeleteFakeStudentByIdMethod) == null) {
          FakeServiceGrpc.getDeleteFakeStudentByIdMethod = getDeleteFakeStudentByIdMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteFakeStudentById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("DeleteFakeStudentById"))
              .build();
        }
      }
    }
    return getDeleteFakeStudentByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest,
      com.google.protobuf.Empty> getUpdateFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateFakeStudent",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest,
      com.google.protobuf.Empty> getUpdateFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest, com.google.protobuf.Empty> getUpdateFakeStudentMethod;
    if ((getUpdateFakeStudentMethod = FakeServiceGrpc.getUpdateFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getUpdateFakeStudentMethod = FakeServiceGrpc.getUpdateFakeStudentMethod) == null) {
          FakeServiceGrpc.getUpdateFakeStudentMethod = getUpdateFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FakeServiceMethodDescriptorSupplier("UpdateFakeStudent"))
              .build();
        }
      }
    }
    return getUpdateFakeStudentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpsertFakeStudent",
      requestType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest.class,
      responseType = udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest,
      udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod() {
    io.grpc.MethodDescriptor<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> getUpsertFakeStudentMethod;
    if ((getUpsertFakeStudentMethod = FakeServiceGrpc.getUpsertFakeStudentMethod) == null) {
      synchronized (FakeServiceGrpc.class) {
        if ((getUpsertFakeStudentMethod = FakeServiceGrpc.getUpsertFakeStudentMethod) == null) {
          FakeServiceGrpc.getUpsertFakeStudentMethod = getUpsertFakeStudentMethod =
              io.grpc.MethodDescriptor.<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest, udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpsertFakeStudent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse.getDefaultInstance()))
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
     * Retrieves the details of an existing fake student by its identifier document and Id type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void getFakeStudentByIdDocument(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFakeStudentByIdDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void getFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFakeStudentByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    default void listFakeBaseCenters(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListFakeBaseCentersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void listFakeStudents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListFakeStudentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void createFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateFakeStudentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void deleteFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteFakeStudentByIdMethod(), responseObserver);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void updateFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateFakeStudentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    default void upsertFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> responseObserver) {
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
     * Retrieves the details of an existing fake student by its identifier document and Id type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void getFakeStudentByIdDocument(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFakeStudentByIdDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void getFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFakeStudentByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public void listFakeBaseCenters(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListFakeBaseCentersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void listFakeStudents(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListFakeStudentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void createFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateFakeStudentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void deleteFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteFakeStudentByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void updateFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateFakeStudentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public void upsertFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest request,
        io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> responseObserver) {
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
     * Retrieves the details of an existing fake student by its identifier document and Id type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse getFakeStudentByIdDocument(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFakeStudentByIdDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse getFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFakeStudentByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse listFakeBaseCenters(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListFakeBaseCentersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse listFakeStudents(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListFakeStudentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse createFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateFakeStudentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.protobuf.Empty deleteFakeStudentById(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteFakeStudentByIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.protobuf.Empty updateFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateFakeStudentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse upsertFakeStudent(udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest request) {
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
     * Retrieves the details of an existing fake student by its identifier document and Id type document. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse> getFakeStudentByIdDocument(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFakeStudentByIdDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieves the details of an existing fake student by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse> getFakeStudentById(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFakeStudentByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all the fake base centers. &lt;br&gt;_Unsecured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse> listFakeBaseCenters(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListFakeBaseCentersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all the fake base students. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse> listFakeStudents(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListFakeStudentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Creates a new fake student. Returns its auto-generated internal identifier if the create succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse> createFakeStudent(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateFakeStudentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Deletes the exisiting fake student specified by its internal identifier. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteFakeStudentById(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteFakeStudentByIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Updates the exisiting fake student specified by its internal identifier. All fake student's properties will be changed, setting the passed values. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateFakeStudent(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateFakeStudentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Creates a new fake student or updates the exisiting fake student based on the value of its internal identifier. Returns its auto-generated internal identifier if the upsert succeeded. &lt;br&gt;_Secured endpoint_.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse> upsertFakeStudent(
        udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpsertFakeStudentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_FAKE_STUDENT_BY_ID_DOCUMENT = 0;
  private static final int METHODID_GET_FAKE_STUDENT_BY_ID = 1;
  private static final int METHODID_LIST_FAKE_BASE_CENTERS = 2;
  private static final int METHODID_LIST_FAKE_STUDENTS = 3;
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
        case METHODID_GET_FAKE_STUDENT_BY_ID_DOCUMENT:
          serviceImpl.getFakeStudentByIdDocument((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse>) responseObserver);
          break;
        case METHODID_GET_FAKE_STUDENT_BY_ID:
          serviceImpl.getFakeStudentById((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse>) responseObserver);
          break;
        case METHODID_LIST_FAKE_BASE_CENTERS:
          serviceImpl.listFakeBaseCenters((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse>) responseObserver);
          break;
        case METHODID_LIST_FAKE_STUDENTS:
          serviceImpl.listFakeStudents((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse>) responseObserver);
          break;
        case METHODID_CREATE_FAKE_STUDENT:
          serviceImpl.createFakeStudent((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse>) responseObserver);
          break;
        case METHODID_DELETE_FAKE_STUDENT_BY_ID:
          serviceImpl.deleteFakeStudentById((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_FAKE_STUDENT:
          serviceImpl.updateFakeStudent((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPSERT_FAKE_STUDENT:
          serviceImpl.upsertFakeStudent((udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest) request,
              (io.grpc.stub.StreamObserver<udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse>) responseObserver);
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
          getGetFakeStudentByIdDocumentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentRequest,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdDocumentResponse>(
                service, METHODID_GET_FAKE_STUDENT_BY_ID_DOCUMENT)))
        .addMethod(
          getGetFakeStudentByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdRequest,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.GetFakeStudentByIdResponse>(
                service, METHODID_GET_FAKE_STUDENT_BY_ID)))
        .addMethod(
          getListFakeBaseCentersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeBaseCentersResponse>(
                service, METHODID_LIST_FAKE_BASE_CENTERS)))
        .addMethod(
          getListFakeStudentsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.ListFakeStudentsResponse>(
                service, METHODID_LIST_FAKE_STUDENTS)))
        .addMethod(
          getCreateFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentRequest,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.CreateFakeStudentResponse>(
                service, METHODID_CREATE_FAKE_STUDENT)))
        .addMethod(
          getDeleteFakeStudentByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.DeleteFakeStudentByIdRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_FAKE_STUDENT_BY_ID)))
        .addMethod(
          getUpdateFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpdateFakeStudentRequest,
              com.google.protobuf.Empty>(
                service, METHODID_UPDATE_FAKE_STUDENT)))
        .addMethod(
          getUpsertFakeStudentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentRequest,
              udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.UpsertFakeStudentResponse>(
                service, METHODID_UPSERT_FAKE_STUDENT)))
        .build();
  }

  private static abstract class FakeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FakeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.getDescriptor();
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
              .addMethod(getGetFakeStudentByIdDocumentMethod())
              .addMethod(getGetFakeStudentByIdMethod())
              .addMethod(getListFakeBaseCentersMethod())
              .addMethod(getListFakeStudentsMethod())
              .addMethod(getCreateFakeStudentMethod())
              .addMethod(getDeleteFakeStudentByIdMethod())
              .addMethod(getUpdateFakeStudentMethod())
              .addMethod(getUpsertFakeStudentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
