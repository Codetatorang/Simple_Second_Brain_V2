db.createCollection("notes")

db.getCollection('notes').insertOne({
    title: 'My note',
    owner: 'test',
    content: 'This is my note, lorem ipsum... not my friend, is siva friend',
    createdAt: new Date(),
    updatedAt: new Date()
});

db.notes.find({owner:"test"})