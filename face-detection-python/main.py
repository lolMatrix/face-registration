import cv2
import numpy as np
from kafka import KafkaConsumer, KafkaProducer

DNN_IMAGE_SIZE = (300, 300)
DNN_IMAGE_SCALE = 1.0
DNN_MEAN_SUBTRACTION_VALUES = (104.0, 177.0, 123.0)
PROTOTXT_PATH = "dnn/deploy.prototxt.txt"
MODEL_PATH = "dnn/res10_300x300_ssd_iter_140000_fp16.caffemodel"

dnn_face_detection_model = cv2.dnn.readNetFromCaffe(PROTOTXT_PATH, MODEL_PATH)
consumer = KafkaConsumer('face.detect')
consumer.poll()
producer = KafkaProducer()


def process_image(image):
    image_height, image_width = image.shape[:2]

    blob = cv2.dnn.blobFromImage(image, DNN_IMAGE_SCALE, DNN_IMAGE_SIZE, DNN_MEAN_SUBTRACTION_VALUES)
    dnn_face_detection_model.setInput(blob)
    face_detection_result = np.squeeze(dnn_face_detection_model.forward())

    for mean_face_recognition in range(0, face_detection_result.shape[0]):
        face_detected_confidence = face_detection_result[mean_face_recognition, 2]
        if face_detected_confidence > 0.5:
            face_box = face_detection_result[mean_face_recognition, 3:7] * np.array(
                [image_width, image_height, image_width, image_height])
            left_face_x, upper_face_y, right_face_x, bottom_face_y = face_box.astype(int)
            return image[left_face_x:right_face_x, upper_face_y:bottom_face_y]


def main():
    while True:
        for record in consumer:
            headers = dict(record.headers)
            if record.key == b"detection_image" and "id" in headers:
                message_id = headers["id"]
                image_bytes = np.frombuffer(record.value, np.uint8)
                image = cv2.imdecode(image_bytes, 1)
                processed_image = process_image(image)
                processed_image_bytes = cv2.imencode('.jpg', processed_image)[1]
                print("face detected")
                producer.send(topic="face.detect",
                              key=b"detected_face",
                              value=processed_image_bytes.tobytes(),
                              headers=[("Correlid", message_id)])


if __name__ == '__main__':
    main()
