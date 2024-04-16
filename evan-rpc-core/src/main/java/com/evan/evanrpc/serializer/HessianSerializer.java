package com.evan.evanrpc.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements Serializer {

//    @Override
//    public <T> byte[] serialize(T object) throws IOException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        HessianOutput ho = new HessianOutput(bos);
//        ho.writeObject(object);
//        return bos.toByteArray();
//    }
//
//    @Override
//    public <T> T deserialize(byte[] bytes, Class<T> tClass) throws IOException {
//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        HessianInput hi = new HessianInput(bis);
//        return (T) hi.readObject(tClass);
//    }

    @Override
    public <T> byte[] serialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);
            output.writeObject(t);
            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null) {
            return null;
        }
        Object result;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);
            result = input.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) result;
    }

}
